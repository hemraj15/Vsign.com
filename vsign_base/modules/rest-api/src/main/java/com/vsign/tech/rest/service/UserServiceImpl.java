package com.vsign.tech.rest.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.auth.util.AuthorizationUtil;
import com.vsign.tech.data.dao.CustomerDao;
import com.vsign.tech.data.dao.EmployeeDao;
import com.vsign.tech.data.dao.UserDao;
import com.vsign.tech.data.dao.entity.Customer;
import com.vsign.tech.data.dao.entity.Employee;
import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.dto.UserDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.message.exception.MailNotSentException;
import com.vsign.tech.message.model.MailMessage;
import com.vsign.tech.message.service.MailService;
import com.vsign.tech.message.utils.ReadConfigurationFile;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.constant.UserStatus;
import com.vsign.tech.rest.constant.UserTypes;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.PasswordException;
import com.vsign.tech.rest.exception.SignUpException;
import com.vsign.tech.rest.exception.StatusException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.ResetPasswordForm;
import com.vsign.tech.rest.form.SignUpStep1Form;
import com.vsign.tech.rest.utils.PasswordUtils;
import com.vsign.tech.rest.utils.RestClientUtils;
import com.vsign.tech.rest.utils.ValidationUtils;

@Service
public class UserServiceImpl implements UserService {
	private Logger		LOGGER	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao		userDao;

	@Autowired
	private CustomerDao	custDao;

	@Autowired
	private EmployeeDao	empDao;

	@Autowired
	private MailService	mailService;

	private void validateInitiateSignUpRequest(String email) throws SignUpException {
		try {
			User user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email.trim()));
			if (user != null) {
				UserStatus status = UserStatus.valueOf(user.getStatus());
				switch (status) {
				case SIGNUP_INITIATED:
					throw new SignUpException("User already initiated Sign Up Process!",
					        ErrorCodes.SIGNUP_ALREADY_INITIATED);
				case ACTIVE:
					throw new SignUpException("User already registered with this Email!",
					        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
				case FORGET_PASSWORD_INITIATED:
					throw new SignUpException("User already registered with this Email!",
					        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
				case ARCHIVED:
					throw new SignUpException(
					        "Your account is deactivated, Please contact your adminisrator!",
					        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);
				default:
					break;
				}
			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error("User does not exist. Valid scenario, do nothing.");
		}
	}

	private void saveUser(SignUpStep1Form signUpStep1Form) throws SignUpException {
		try {
			User user = new User();
			user.setFirstName(signUpStep1Form.getFirstName());
			user.setLastName(signUpStep1Form.getLastName());
			user.setEmailId(signUpStep1Form.getEmail());
			user.setTempPassword(signUpStep1Form.getPassword());
			user.setUserType(signUpStep1Form.getUserType());
			String salt = BCrypt.gensalt(12);
			user.setPassword(BCrypt.hashpw(signUpStep1Form.getPassword(), salt));
			user.setStatus(UserStatus.SIGNUP_INITIATED.toString());

			LOGGER.debug("Savig User");
			userDao.save(user);
			LOGGER.debug("User saved");

			if (signUpStep1Form.getUserType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())) {
				saveCustomer(signUpStep1Form);
			} else {
				saveEmployee(signUpStep1Form);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SignUpException("Error occurred while creating new User in database!",
			        ErrorCodes.DATABASE_ERROR);
		}
	}

	private void saveEmployee(SignUpStep1Form signUpStep1Form) {

		Employee emp = new Employee();
		emp.setFirstName(signUpStep1Form.getFirstName());
		emp.setLastName(signUpStep1Form.getLastName());
		emp.setStatus(UserStatus.ACTIVE.toString());
		emp.setEmail(signUpStep1Form.getEmail());
		LOGGER.debug("Savig Employee");
		empDao.save(emp);
		LOGGER.debug("Employee saved with status :" + UserStatus.SIGNUP_INITIATED.toString());

	}

	private void saveCustomer(SignUpStep1Form signUpStep1Form) {

		Customer cust = new Customer();

		cust.setFirstName(signUpStep1Form.getFirstName());
		cust.setLastName(signUpStep1Form.getLastName());
		cust.setStatus(UserStatus.ACTIVE.toString());
		cust.setEmail(signUpStep1Form.getEmail());

		LOGGER.debug("Savig Customer");
		custDao.save(cust);
		LOGGER.debug("Customer saved :" + UserStatus.ACTIVE.toString());

	}

	private void sendSignUpEmail(String email, String firstName, String lastName)
	        throws SignUpException {
		String url = ReadConfigurationFile.getProperties("ui-redirection-urls.properties")
		        .getProperty("signup.url");

		MailMessage mailHtmlMessage = new MailMessage();
		mailHtmlMessage.setSubject("Welcome to Prinkaari !! ");
		mailHtmlMessage.setContent("<h2>Hello " + firstName + " " + lastName
		        + "!</h2><h3>Please click on below link to activate your account</h3>" + " <a href="
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + ">"
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + "</a>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SignUpException("Error occurred while sending Sign Up Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent");
	}

	@Override
	@Transactional
	public void initiateSignUp(SignUpStep1Form signUpstep1Form) throws SignUpException {
		// Validating Intiate Sign Up Request
		validateInitiateSignUpRequest(signUpstep1Form.getEmail());

		// Creating new user
		saveUser(signUpstep1Form);

		// Sending Welcome Email to user
		/*sendSignUpEmail(signUpstep1Form.getEmail(), signUpstep1Form.getFirstName(),
		        signUpstep1Form.getLastName());*/
	}

	@Override
	@Transactional
	public void resendEmail(String token) throws SignUpException {
		if (token == null || token.trim().length() <= 0) {
			throw new SignUpException("Email token should not be empty or null.",
			        ErrorCodes.VALIDATION_ERROR);
		}
		try {
			LOGGER.debug("Resend Email");
			String email = PasswordUtils.decode(token);
			if (!ValidationUtils.validateEmail(email.trim())) {
				throw new SignUpException("Please provide valid Email.",
				        ErrorCodes.VALIDATION_ERROR);
			}

			User user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email.trim()));
			if (user == null) {
				throw new SignUpException("No user found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}
			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {
			case SIGNUP_INITIATED:
				sendSignUpEmail(email, user.getFirstName(), user.getLastName());
				break;
			case ACTIVE:
				throw new SignUpException("User already registered with this Email!",
				        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
			case FORGET_PASSWORD_INITIATED:
				throw new SignUpException("User already registered with this Email!",
				        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
			case ARCHIVED:
				throw new SignUpException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);
			default:
				break;

			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SignUpException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}

	}

	@Override
	@Transactional
	public void sendForgotPasswordLink(String emailId)
	        throws MailNotSentException, PasswordException, UserNotFoundException {
		if (!ValidationUtils.validateEmail(emailId.trim())) {
			throw new UserNotFoundException("Please provide valid Email.",
			        ErrorCodes.VALIDATION_ERROR);
		}

		User user = validateForgetPasswordRequest(emailId.trim());

		updateUser(user);
	}

	private void updateUser(User user) throws PasswordException {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		sendForgotPasswordEmail(user.getEmailId(), firstName, lastName);
		user.setStatus(UserStatus.FORGET_PASSWORD_INITIATED.toString());
		userDao.update(user);
	}

	private User validateForgetPasswordRequest(String emailId)
	        throws PasswordException, UserNotFoundException {
		User user = null;
		try {
			user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(emailId.trim()));
			if (user == null) {
				throw new UserNotFoundException("No user found with this email id",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}

			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {
			case SIGNUP_INITIATED:
				throw new UserNotFoundException(
				        "You have already initiated the signup, please complete it first!",
				        ErrorCodes.FORGOT_REQUEST_SIGNUP_INITIATED);
			case ARCHIVED:
				throw new UserNotFoundException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);
			default:
				break;
			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new UserNotFoundException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}
		return user;
	}

	@Override
	@Transactional
	public void resetPassword(ResetPasswordForm resetPasswordForm)
	        throws PasswordException, UserNotFoundException {
		String token = resetPasswordForm.getEmailToken();
		String newPassword = resetPasswordForm.getNewPassword();
		LOGGER.debug("Resetting password");
		String email = PasswordUtils.decode(token);
		if (!ValidationUtils.validateEmail(email.trim())) {
			throw new PasswordException("Please provide valid token.", ErrorCodes.VALIDATION_ERROR);
		}

		User user;
		try {
			user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email.trim()));
			if (user == null) {
				throw new UserNotFoundException("No user found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}

			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {
			case FORGET_PASSWORD_INITIATED:
				String salt = BCrypt.gensalt(12);

				user.setPassword(BCrypt.hashpw(newPassword, salt));

				user.setStatus(UserStatus.ACTIVE.toString());
				userDao.update(user);
				break;

			case SIGNUP_INITIATED:
				throw new PasswordException(
				        "You have already initiated the signup, please complete it first!",
				        ErrorCodes.FORGOT_REQUEST_SIGNUP_INITIATED);

			case ACTIVE:
				throw new PasswordException("User is in  already active state",
				        ErrorCodes.RESET_PASSWORD_USER_ALREADY_ACTIVE);

			case ARCHIVED:
				throw new PasswordException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);

			default:
				break;
			}

		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new UserNotFoundException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}

	}

	private void sendForgotPasswordEmail(String email, String firstName, String lastName)
	        throws PasswordException {
		String url = ReadConfigurationFile.getProperties("ui-redirection-urls.properties")
		        .getProperty("forgot.password.url");

		MailMessage mailHtmlMessage = new MailMessage();
		mailHtmlMessage.setSubject("Welcome to Printkaari!");
		mailHtmlMessage.setContent("<h2>Hello " + firstName + " " + lastName
		        + "!</h2><h3>Please click on below link to reset your password</h3>" + " <a href="
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + ">"
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + "</a>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new PasswordException("Error occurred while sending Forgot password  Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent");
	}

	@Override
	@Transactional
	public String loginUser(String token, String password)
	        throws UsernameNotFoundException, PasswordException, Exception {
		LOGGER.info("User Srevice impl token :" + token);
		LOGGER.debug("User Srevice impl password :" + password);

		validateLoginRequest(token, password);
		Properties props = ReadConfigurationFile.getProperties("auth_server.properties");
		String authTokenResponse = RestClientUtils.autoLogin(PasswordUtils.decode(token), password,
		        props.getProperty("aGrantType"), props.getProperty("aClientId"),
		        props.getProperty("aClientSecret"), props.getProperty("aScope"),
		        props.getProperty("aHost"), props.getProperty("aPort"));
		LOGGER.debug("Auth token response " + authTokenResponse);

		return authTokenResponse;
	}

	private void validateLoginRequest(String token, String password)
	        throws Exception, PasswordException {
		try {
			User user = (User) userDao
			        .getByCriteria(userDao.getFindByEmailCriteria(PasswordUtils.decode(token)));
			if (user == null) {

				throw new UserNotFoundException("No user found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}
			/*
			 * else if (!(user.getStatus().equals(UserStatus.ACTIVE.toString()))){
			 * 
			 * 
			 * }
			 */
			UserStatus status = UserStatus.valueOf(user.getStatus());

			switch (status) {
			case FORGET_PASSWORD_INITIATED:
				throw new PasswordException(
				        "Forogto Password Already Initiated , Change the Password ",
				        ErrorCodes.FORGET_PASSWORD_INITIATED);

			case SIGNUP_INITIATED:
				throw new StatusException(
				        "You have initiated the signup, please complete it first!",
				        ErrorCodes.SIGNUP_INITIATED);

			case ARCHIVED:
				throw new StatusException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);

			default:
				break;
			}

			String salt = BCrypt.gensalt(12);

			String enPassword = BCrypt.hashpw(password, salt);
			/*
			 * if(!(user.getPassword().equals(enPassword))){
			 * 
			 * 
			 * System.out.println("DB Password is "+user.getPassword()+" Provided Pawword is"
			 * +enPassword); throw new PasswordException("Invalid Password ",
			 * ErrorCodes.PASSWORD_INVALID); }
			 */

		} catch (InstanceNotFoundException e) {
			LOGGER.debug("validate user error : User not found " + e.getMessage());
			throw new UserNotFoundException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}

	}

	@Override
	@Transactional
	public String autoLoginUser(String token, String password) throws InstanceNotFoundException {
		LOGGER.info("User Srevice impl token :" + token);
		LOGGER.debug("User Srevice impl password :" + password);

		Properties props = ReadConfigurationFile.getProperties("auth_server.properties");
		String authTokenResponse = RestClientUtils.autoLogin(PasswordUtils.decode(token), password,
		        props.getProperty("aGrantType"), props.getProperty("aClientId"),
		        props.getProperty("aClientSecret"), props.getProperty("aScope"),
		        props.getProperty("aHost"), props.getProperty("aPort"));
		LOGGER.debug("Auth token response " + authTokenResponse);
		User user = (User) userDao
		        .getByCriteria(userDao.getFindByEmailCriteria(PasswordUtils.decode(token)));
		user.setTempPassword("");
		userDao.update(user);
		return authTokenResponse;
	}

	@Override
	@Transactional
	public List<UserDto> recruiterDTOList() throws EmptyListException {
		List<UserDto> userDtos = new ArrayList<>();
		try {
			User user = AuthorizationUtil.getLoggedInUser();
			// Long companyId = user.getCompany().getId();

			Long companyId = null;

			if (userDtos.isEmpty() || userDtos == null) {
				throw new EmptyListException("User Lsit is empty",
				        ErrorCodes.RECRUIERS_LIST_EMPTY);
			}

		} catch (Exception e) {
			throw new EmptyListException(
			        "Error occured while getting user list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		return userDtos;
	}

	@Override
	@Transactional
	public String getEmail() {
		User user = AuthorizationUtil.getLoggedInUser();
		return user.getEmailId();
	}

}
