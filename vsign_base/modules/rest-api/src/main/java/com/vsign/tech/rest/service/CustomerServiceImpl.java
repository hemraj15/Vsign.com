package com.vsign.tech.rest.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.auth.util.AuthorizationUtil;
import com.vsign.tech.data.dao.CustomerDao;
import com.vsign.tech.data.dao.CustomerFileDao;
import com.vsign.tech.data.dao.CustomersMachineWithPlansDao;
import com.vsign.tech.data.dao.OrderDao;
import com.vsign.tech.data.dao.PlansDao;
import com.vsign.tech.data.dao.ProductDao;
import com.vsign.tech.data.dao.TransacationOrderDao;
import com.vsign.tech.data.dao.UserDao;
import com.vsign.tech.data.dao.entity.CustOrder;
import com.vsign.tech.data.dao.entity.Customer;
import com.vsign.tech.data.dao.entity.CustomerFiles;
import com.vsign.tech.data.dao.entity.CustomersMachineWithPlans;
import com.vsign.tech.data.dao.entity.Plans;
import com.vsign.tech.data.dao.entity.Product;
import com.vsign.tech.data.dao.entity.TransacationOrder;
import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.dto.CustomerDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.message.exception.MailNotSentException;
import com.vsign.tech.message.model.MailMessage;
import com.vsign.tech.message.service.MailService;
import com.vsign.tech.message.utils.ReadConfigurationFile;
import com.vsign.tech.rest.constant.CommonStatus;
import com.vsign.tech.rest.constant.CostConstant;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.constant.ProductCodes;
import com.vsign.tech.rest.constant.UserTypes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.InvalidMachineIdException;
import com.vsign.tech.rest.exception.InvalidNumberOfPagesException;
import com.vsign.tech.rest.exception.InvalidProductException;
import com.vsign.tech.rest.exception.InvalidQuantiryException;
import com.vsign.tech.rest.exception.MailNotSendException;
import com.vsign.tech.rest.exception.ProductNotFoundException;
import com.vsign.tech.rest.exception.StatusException;
import com.vsign.tech.rest.exception.TransactionOrderNotFoundException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.GuestForm;
import com.vsign.tech.rest.utils.CommonUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Logger					LOGGER				= LoggerFactory
	        .getLogger(CustomerServiceImpl.class);

	private static String			BASE_UPLOAD_PATH	= "";

	@Autowired
	private UserDao					userDao;

	@Autowired
	private ProductDao				prodDao;

	@Autowired
	private OrderDao				ordDao;

	@Autowired
	private CustomerFileDao			custFileDao;

	@Autowired
	private CustomerDao				custDao;

	@Autowired
	private MailService				mailService;

	@Autowired
	private TransacationOrderDao	trxOrderDao;
	@Autowired
	private PlansDao plansDao;
	@Autowired
	private CustomersMachineWithPlansDao cMWPlans;

	static {
		Properties props = ReadConfigurationFile.getProperties("file-upload.properties");
		BASE_UPLOAD_PATH = props.getProperty("base_upload_path");
	}

	@Override
	@Transactional
	public List<CustomerDto> fetchAllCustomerByModifyDate(Integer records)
	        throws DatabaseException {
		List<CustomerDto> customerDtos = null;
		Integer toIndex = records;

		try {
			customerDtos = custDao.fetchAllCustomerByModifyDate(0, toIndex,
			        CommonStatus.ACTIVE.toString());

		} catch (Exception e) {
			LOGGER.error("Error occured while getting fetchAllCustomerByModifyDate list through database", e);
			throw new DatabaseException(
			        "Error occured while getting fetchAllCustomerByModifyDate through database",
			        ErrorCodes.DATABASE_ERROR);
		}
		return customerDtos;

	}

	@Override
	@Transactional
	public Object fetchAllOrdersByCustomerId(String customerMailId) throws DatabaseException {

		List<CustOrder> custOrders = null;
		Customer customer = null;

		try {

			customer = getCustomerByEmailId(customerMailId);

			if (customer != null) {
				custOrders = ordDao.fetchAllOrdersByCustomerId(customer.getId());
			} else {

				throw new UserNotFoundException(
				        " Customer not found Error occured while getting all orders for a customer through database",
				        ErrorCodes.DATABASE_ERROR);
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while gettingfetchAllOrdersByCustomerId through database", e);
			e.printStackTrace();
			throw new DatabaseException(
			        "Error occured while getting all orders for a customer through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		return custOrders;
	}

	/**
	 * @param customerMailId
	 * @return
	 * @throws InstanceNotFoundException
	 */
	private Customer getCustomerByEmailId(String customerMailId) throws InstanceNotFoundException {
		Customer customer;
		customer = (Customer) custDao.getByCriteria(custDao.getFindByEmailCriteria(customerMailId));
		return customer;
	}

	@Override
	public List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer records)
	        throws DatabaseException {

		return null;
	}

	@Override
	public String fetchLoggedinCustomer() throws DatabaseException, UserNotFoundException {
		String email = "";
		User user = (User) AuthorizationUtil.getLoggedInUser();
		if (user.getUserType().equals(UserTypes.CUSTOMER.toString())) {
			email = user.getEmailId();
		} else {

			throw new UserNotFoundException("Logged in user is not a customer",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}

		return email;
	}

	@Override
	@Transactional
	public Object fetchCustomerByEmail(String email) throws DatabaseException,
	        UserNotFoundException, InstanceNotFoundException, StatusException {
		Customer cust = null;
		User user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email));
		if (user == null) {

			System.out.println("user is null");
			throw new UserNotFoundException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		} else {

			System.out.println("user found " + user.getUserType());
			System.out.println("user status :" + user.getStatus());

			if (user.getUserType().equals(UserTypes.CUSTOMER.toString())
			        && user.getStatus().equals(CommonStatus.ACTIVE.toString())) {

				cust = getCustomerByEmailId(user.getEmailId());

			}
			if (cust != null) {

				System.out.println("Customer found +" + cust.getFirstName());
				System.out.println("customer status" + cust.getStatus());
				System.out.println("customer email" + cust.getEmail());
			} else {

				throw new StatusException("User is not a customer or Inactive Customer");
			}

		}

		return cust;
	}

	@Override
	@Transactional
	public User getLoggedinUser() {

		System.out.println("Calling get logged in user from customer service impl ");
		return AuthorizationUtil.getLoggedInUser();
	}

	@Override
	@Transactional
	public Object fetchAllActiveOrdersByCustomerId(String userEmail, String status)
	        throws DatabaseException {
		List<CustOrder> custOrders = null;
		Customer customer = null;

		// Map<String ,Object> data =new HashMap<>();

		try {

			customer = getCustomerByEmailId(userEmail);
			if (customer != null) {

				custOrders = ordDao.fetchAllActiveOrdersByCustomerId(customer.getId(), status);
			} else {

				LOGGER.info("getCustomerByeEmailId returned null cust object");
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while fetchAllActiveOrdersByCustomerId through database", e);
			e.printStackTrace();
			throw new DatabaseException(
			        "Error occured while getting all orders for a customer through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		return custOrders;
	}

	@Override
	@Transactional
	public Map<String, Object> placeCollegeOrder(Integer glossyColorPages,
	        Integer nonGlossyColorPages, String anyOtherRequest, Integer totalPages,
	        String bindingType, Long fileId, Integer totalColorPages, Integer quantity,
	        String colorPages) throws DatabaseException, InvalidProductException,
	        MailNotSendException, InvalidNumberOfPagesException, InvalidQuantiryException {
		Long orderId = null;
		String productCode = null;
		CustOrder custOrder = new CustOrder();
		Product product = null;
		User user = null;
		Customer cust = null;
		Double basePrice = 0.0;
		Double discountAmount = 0.0;
		Double amountToBePaid = 0.0;
		Integer discount = 0;
		Map<String, Object> map = new HashMap<>();
		Integer blackPage = totalPages - totalColorPages;
		Integer otherColorPages= totalColorPages-(glossyColorPages+nonGlossyColorPages)>0?totalColorPages-(glossyColorPages+nonGlossyColorPages):0;
		try {

			if (totalPages < totalColorPages) {

				throw new InvalidNumberOfPagesException(
				        "total pages can not be less than color pages ",
				        ErrorCodes.INVALID_NUMBER_OF_PAGES);
			}

			if (quantity < 2 && quantity >= 0) {
				discount = 15;
			} else if (quantity >= 2) {
				discount = 25;
			} else {

				throw new InvalidQuantiryException("Invalid print quantity supplied ",
				        ErrorCodes.INVALID_PRINT_QUANTITY);
			}
			LOGGER.info("Place CustOrder Customer Service Impl");
			LOGGER.info("Place CustOrder for Bindig type ::" + bindingType);
			if (bindingType.equalsIgnoreCase("hard")) {
				productCode = ProductCodes.HARD_BINDING.toString();
				basePrice = CostConstant.hard_binnding_base_rate;
			} else if (bindingType.equalsIgnoreCase("spiral")) {
				productCode = ProductCodes.SPIRAL_BINDING.toString();
				basePrice = CostConstant.spiral_binding_base_rate;
			} else {

				LOGGER.info("Invalid Product type for College Projects Catagory");

				throw new InvalidProductException("Invalid Product for College Projects Catagory !",
				        ErrorCodes.INVALID_PRODUCT_ERROR);
			}
			user = (User) getLoggedinUser();

			LOGGER.info("Product code for College Projects Catagory " + productCode);

			if (user != null && user.getUserType().equals(UserTypes.CUSTOMER.toString())) {
				LOGGER.info("Initiate order Logged in user is " + user.getFirstName() + " user id  "
				        + user.getId());
				cust = getCustomerByEmailId(user.getEmailId().trim());
				if (cust != null) {

					LOGGER.info("Initiate order customer is " + cust.getFirstName()
					        + " customer id  " + cust.getId());
					Double totalPrice = basePrice
					        + (glossyColorPages * CostConstant.color_glossy_page)
					        + (nonGlossyColorPages * CostConstant.color_non_glossy_page)
					        + (blackPage * CostConstant.simple_black_page)
					        + (otherColorPages* CostConstant.color_page_simple);
					
					System.out.println("totalPrice before round off :" + totalPrice);
					totalPrice=totalPrice*quantity;
					totalPrice=Math.round(totalPrice*100)/100D;
					

					System.out.println("totalPrice after round off :" + totalPrice);
					product = fetchProductCodeFromDB(productCode);

					if (product == null) {

						LOGGER.info("no product found for product code " + productCode);
						throw new ProductNotFoundException(
						        "Product not found in the data base while placing order ",
						        ErrorCodes.PRODUCT_NOT_FOUND_IN_DATABASE);

					} else {
						LOGGER.info(
						        "Product found for product code :: " + product.getProductCode());
						LOGGER.info("Product id :: " + product.getId());
						LOGGER.info(
						        "Product sample file id  :: " + product.getSampleFileId().getId());
						LOGGER.info("Product sample file path  :: "
						        + product.getSampleFileId().getFilePath());
						discountAmount=totalPrice * (discount / 100.0);
						System.out.println("discount amount before round off :" + discountAmount);
						discountAmount = Math.round(discountAmount*100)/100D;
						System.out.println("discount  :" + discount);
						System.out.println("discount amount after round off :" + discountAmount);
						amountToBePaid = totalPrice - discountAmount;
						System.out.println("Amount to be paid :" + amountToBePaid);

						custOrder.setCustomer(cust);
						custOrder.setDescription(anyOtherRequest);
						custOrder.setStatus(CommonStatus.INITIATED.toString());
						Set<Product> sets = new HashSet<>();
						sets.add(product);
						custOrder.setOrderPrice(totalPrice);
						custOrder.setPaidAmount(amountToBePaid);
						custOrder.setDiscountAmount(discountAmount);

						Set<CustomerFiles> fileSet = new HashSet<>();

						fileSet.add(custFileDao.find(fileId));

						//custOrder.setFileId(fileSet);

						custOrder.setCreatedBy(cust.getFirstName());

						orderId = ordDao.save(custOrder);
						map.put("orderId", orderId);
						map.put("amountToBePaid", custOrder.getPaidAmount());
						map.put("Discount", discountAmount);
						map.put("Amount", custOrder.getPaidAmount() + discountAmount);
						map.put("productSample", BASE_UPLOAD_PATH + File.separator
						        + product.getSampleFileId().getFilePath());
						map.put("productCode", product.getProductCode());
						map.put("productName", product.getName());
						map.put("productId", product.getId());
						map.put("quantity", quantity);

						LOGGER.info("OrderDao Save CustOrder --> initiated with CustOrder Id " + orderId);

						sendOrderStatusMailToCustomer(orderId, custOrder.getStatus(), cust);
						sendOrderStatusMailToAdmin(orderId, cust);

					}

				} else {
					LOGGER.info(
					        "Customer object  is null or is not a customer  while placing order ");
				}
			} else {

				LOGGER.info("User is null or is not a customer  while placing order ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while executing placeCollegeOrder in  database", e);
			e.printStackTrace();
			throw new DatabaseException(
			        "Error occured while getting all orders for a customer through database",
			        ErrorCodes.DATABASE_ERROR);
		}
		return map;
	}

	/**
	 * @param productCode
	 * @return
	 * @throws InstanceNotFoundException
	 */
	private Product fetchProductCodeFromDB(String productCode) throws InstanceNotFoundException {
		Product product;
		product = (Product) prodDao.getByCriteria(prodDao.getByProductCode(productCode));
		return product;
	}

	private void sendOrderStatusMailToCustomer(Long orderId, String ordStatus, Customer cust)
	        throws MailNotSendException {
		MailMessage mailHtmlMessage = new MailMessage();
		String email = cust.getEmail();
		mailHtmlMessage.setSubject("Your CustOrder Status here  !! ");
		mailHtmlMessage.setContent("<h2>Hello " + cust.getFirstName() + " " + cust.getLastName()
		        + "!</h2><h3> Your CustOrder Number - " + orderId + " Status is " + ordStatus
		        + " you can track your order  "
		        + "<a href=www.printkaari.com/#!/auth/login > here  </a></h3>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSendException("Error occurred while sending CustOrder Status Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent to Customer");

	}

	private void sendOrderStatusMailToAdmin(Long orderId, Customer cust)
	        throws InstanceNotFoundException, MailNotSendException {

		try {
			User user = getAdminUser();
			String email = user.getEmailId();
			MailMessage mailHtmlMessage = new MailMessage();
			mailHtmlMessage.setSubject("CustOrder Status mail !! ");
			mailHtmlMessage.setContent("<h2>Hello " + user.getFirstName() + "</h2> <h3> Customer "
			        + cust.getFirstName() + "  " + cust.getLastName() + " Customer ID : "
			        + cust.getId() + " has updated/placed order  number - " + orderId
			        + " track order  "
			        + " <a href=www.printkaari.com/#!/auth/login >here</a> </h3>");
			mailHtmlMessage.setToAddresses(new String[] { email });

			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSendException("Error occurred while sending order Status Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent to ADMIN");

	}

	/**
	 * @return
	 * @throws InstanceNotFoundException
	 */
	private User getAdminUser() throws InstanceNotFoundException {
		User user = (User) userDao.getByCriteria(userDao.getFingByeUserRole(SystemRoles.ADMIN));
		return user;
	}

	private TransacationOrder getTrxOrderByTrxOrderId(Long trxOrderId)
	        throws InstanceNotFoundException {

		return trxOrderDao.find(trxOrderId);
	}

	@Override
	@Transactional
	public String confirmOrder(Long trxOrderId, String successCode) throws DatabaseException {

		List<CustOrder> custOrders = new ArrayList<>();
		Long orderId = null;
		TransacationOrder trxOrder = null;
		Customer cust = null;
		int counter = 0;
		String licenseKey =" " ;
		try {

			trxOrder = getTrxOrderByTrxOrderId(trxOrderId);
			custOrders = trxOrder != null ? trxOrder.getOrders() : custOrders;

			Iterator<CustOrder> itr = custOrders.iterator();
			if (trxOrder != null && successCode != null
			        && successCode.equalsIgnoreCase("ok")) {

				trxOrder.setStatus(CommonStatus.COMPLETE.toString());
				trxOrderDao.update(trxOrder);

				for (CustOrder ord : custOrders) {

					counter++;
					// CustOrder ord = itr.next();
					orderId = ord.getId();

					LOGGER.info("CustOrder " + orderId + " found");
					ord.setStatus(CommonStatus.COMPLETE.toString());
					ordDao.update(ord);
					LOGGER.info("CustOrder " + orderId + " is confirmed ");
					cust = ord.getCustomer();
					if (cust != null) {
						
						licenseKey = getLicenseKey(cust,orderId);
						LOGGER.error("Customer associated with CustOrder " + orderId
						        + " is found >> sending mails");
						/*sendOrderStatusMailToCustomer(ord.getId(), CommonStatus.ACTIVE.toString(),
						        cust);
						sendOrderStatusMailToAdmin(ord.getId(), cust);*/
						LOGGER.error("Mail sent to customer and admin ");
					} else {
						LOGGER.error("Customer associated with CustOrder " + orderId + " is null");
					}

				}
			}

			else {

				LOGGER.info("transaction order not found for " + trxOrderId);
				LOGGER.error("Error occured while updating order in database");
				throw new TransactionOrderNotFoundException(
				        "Error occured while updating order in database - trx status is not success ",
				        ErrorCodes.TRX_ORDER_NOT_FOUND_ERROR);

			}

			if (custOrders.size() > 0) {

				cust = custOrders.get(0).getCustomer();
				/*sendPaymentConfirmationMailToAdmin(trxOrder.getId(), cust, successCode);
				sendPaymentConfirmationMailToCustomer(trxOrder.getId(), cust, successCode);*/
				LOGGER.error("Payment Confirmation Mail sent to customer and admin ");
			}

			/*
			 * 
			 * 
			 * LOGGER.info("CustOrder about to confirm :" + orderId); LOGGER.info(
			 * "Success Code to confirm order :" + successCode); CustOrder ord =
			 * getOrderByOrderId(orderId); if (ord != null && successCode !=null) { LOGGER.info(
			 * "CustOrder " + orderId + " found"); ord.setStatus(CommonStatus.ACTIVE.toString());
			 * ordDao.update(ord); LOGGER.info("CustOrder " + orderId + " is confirmed "); Customer cust
			 * = ord.getCustomer(); if (cust != null) { LOGGER.error(
			 * "Customer associated with CustOrder " + orderId + " is found >> sending mails");
			 * sendOrderStatusMailToCustomer(ord.getId(), CommonStatus.ACTIVE.toString(), cust);
			 * sendOrderStatusMailToAdmin(ord.getId(), cust); LOGGER.error(
			 * "Mail sent to customer and admin "); } else { LOGGER.error(
			 * "Customer associated with CustOrder " + orderId + " is null"); }
			 * 
			 * } else{ LOGGER.error(" Success Code is null !!"); }
			 */

		} catch (Exception e) {
			LOGGER.error("Error occured while updating order in database", e);
			e.printStackTrace();
			throw new DatabaseException("Error occured while updating order in database",
			        ErrorCodes.DATABASE_ERROR);
		}
		return licenseKey;

	}

	private String getLicenseKey(Customer cust, Long orderId) {
		
		Plans plan ;
		CustOrder ord;
		CustomersMachineWithPlans withPlans ;
		Integer duration=0;
		String key="";
		try {
			ord=getOrderByOrderId(orderId);
			plan=getPlanByPlanId(ord.getPlanId());
			if (ord !=null && ord.getStatus().equalsIgnoreCase(CommonStatus.COMPLETE.toString())) {
				withPlans=new CustomersMachineWithPlans();
				withPlans.setCustId(cust.getId());
				withPlans.setMachinId(ord.getMachinId());
				//withPlans.setPlanExpiryDate(plan.get);
				withPlans.setDateOfPurchase(new Date());
				duration=Integer.valueOf(plan.getDurationinMonths());
				Integer yearsValidity= duration/12;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, yearsValidity);
				withPlans.setPlanExpiryDate(cal.getTime());
				withPlans.setPlanId(plan.getId());
				withPlans.setStatus(CommonStatus.COMPLETE.toString());
				//withPlans.set
				
				key=generateKey(cust.getFirstName(),1,plan.getPlanName());
				withPlans.setLicenseKey(key);
				cMWPlans.save(withPlans);
			}
			
			
		} catch (Exception e) {
			
		}
		
		return key;
	}

	private String generateKey(String userName,Integer versionNumber,String pName ) {
		/* final String s = userName + "|" + pName + "|" + versionNumber;
	        final HashFunction hashFunction = Hashing.sha1();
	        final HashCode hashCode = hashFunction.hashString(s);
	        final String upper = hashCode.toString().toUpperCase();
	       // return group(upper);
		//return null;
*/	        
	        final String s1 =CommonUtils.randomAlphaString(4)+"-"+"1"+"-"+CommonUtils.randomAlphaNumeric(7)+"-"+CommonUtils.get5digitUniqueNumber()+"-"+CommonUtils.get5digitUniqueNumber()+"-"+CommonUtils.randomAlphaString(4); 
	        return s1;
	}
	
	
	
	
	/* private static String group(String s) {
	        String result = "";
	        for (int i=0; i < s.length(); i++) {
	            if (i%6==0 && i > 0) {
	                result += '-';
	            }
	            result += s.charAt(i);
	        }
	        return result;
	    }*/

	private Plans getPlanByPlanId(Long planId) throws InstanceNotFoundException {
		
		return plansDao.find(planId);
	}

	private void sendPaymentConfirmationMailToCustomer(Long trxOrderId, Customer cust,
	        String successCode) throws MailNotSendException {
		MailMessage mailHtmlMessage = new MailMessage();
		String email = cust.getEmail();
		mailHtmlMessage.setSubject("Payment Confirmation   !! ");
		mailHtmlMessage.setContent("<h2>Hello " + cust.getFirstName() + " " + cust.getLastName()
		        + "! </h2><h3> Payment status for transaction order id " + trxOrderId + " is "
		        + successCode
		        + " , keep this as reference for payment related query , for more details click  "
		        + "<a href=www.printkaari.com/#!/auth/login > here  </a></h3>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSendException("Error occurred while sending CustOrder Status Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent to Customer");

	}

	private void sendPaymentConfirmationMailToAdmin(Long trxOrderId, Customer cust,
	        String successCode) throws InstanceNotFoundException, MailNotSendException {
		try {
			User user = getAdminUser();
			String email = user.getEmailId();
			MailMessage mailHtmlMessage = new MailMessage();
			mailHtmlMessage.setSubject("Payment status mail !! ");
			mailHtmlMessage.setContent("<h2> Hello " + user.getFirstName() + "</h2> <h3> Customer "
			        + cust.getFirstName() + " " + cust.getLastName() + " Customer ID : "
			        + cust.getId() + " has made payment , payment status is " + successCode
			        + " for transaction order id - " + trxOrderId + " track order  "
			        + " <a href=www.printkaari.com/#!/auth/login >here</a> </h3>");
			mailHtmlMessage.setToAddresses(new String[] { email });

			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSendException("Error occurred while sending order Status Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent to ADMIN");

	}

	/**
	 * @param orderId
	 * @return
	 * @throws InstanceNotFoundException
	 */
	private CustOrder getOrderByOrderId(Long orderId) throws InstanceNotFoundException {
		CustOrder ord = ordDao.find(orderId);
		return ord;
	}

	@Override
	@Transactional
	public void changeOrderStatus(String status, Long orderId) throws DatabaseException {
		try {
			LOGGER.info("CustOrder status about to change :" + orderId);
			CustOrder ord = getOrderByOrderId(orderId);
			if (ord != null) {
				ord.setStatus(status);
				ordDao.update(ord);
				LOGGER.info("Status of CustOrder " + orderId + " is updated to  " + status);

				Customer cust = ord.getCustomer();
				if (cust != null) {
					sendOrderStatusMailToCustomer(ord.getId(), status, cust);
					sendOrderStatusMailToAdmin(ord.getId(), cust);
				}

			}
		} catch (Exception e) {
			LOGGER.error("Error occured changeOrderStatus through database", e);
			e.printStackTrace();
			throw new DatabaseException(
			        "Error occured while getting all orders for a customer through database",
			        ErrorCodes.DATABASE_ERROR);
		}

	}

	@Override
	@Transactional
	public void changetrxOrderStatus(String ordStatus, Long trxOrderId) throws DatabaseException {
		try {
			LOGGER.info("CustOrder status about to change :" + trxOrderId);
			TransacationOrder ord = getTrxOrderByTrxOrderId(trxOrderId);
			if (ord != null) {
				ord.setStatus(ordStatus);
				trxOrderDao.update(ord);
				LOGGER.info("Status of CustOrder " + trxOrderId + " is updated to  " + ordStatus);

				Customer cust = !(CollectionUtils.isEmpty(ord.getOrders()))
				        ? ord.getOrders().get(0).getCustomer() : null;
				if (cust != null) {
					sendOrderStatusMailToCustomer(ord.getId(), ordStatus, cust);
					sendOrderStatusMailToAdmin(ord.getId(), cust);
				}

			}
		} catch (Exception e) {
			LOGGER.error("Error occured while changetrxOrderStatus through database", e);
			e.printStackTrace();
			throw new DatabaseException(
			        "Error occured while getting all orders for a customer through database",
			        ErrorCodes.DATABASE_ERROR);
		}

	}

	@Override
	@Transactional
	public void updateCustomerStatus(String status, Long customerId) throws DatabaseException,InstanceNotFoundException {
		
			try {
				LOGGER.info("Customer status about to change :" + customerId);
				Customer cust = getCustomerrByCustOrderId(customerId);
				if (cust != null) {
					cust.setStatus(status);
					custDao.update(cust);
					LOGGER.info("Status of Customer " + customerId + " is updated to  " + status);
					
						sendCustomerStatusMailToCustomer(customerId, status, cust);
						sendCustomerStatusMailToAdmin(customerId, status, cust);
						
					

				}
			} catch (Exception e) {
				LOGGER.error("Error occured while changetrxOrderStatus through database", e);
				e.printStackTrace();
				throw new DatabaseException(
				        "Error occured while getting all orders for a customer through database",
				        ErrorCodes.DATABASE_ERROR);
			}
}

	private void sendCustomerStatusMailToCustomer(Long customerId, String status, Customer cust) throws MailNotSendException {
		MailMessage mailHtmlMessage = new MailMessage();
		String email = cust.getEmail();
		mailHtmlMessage.setSubject("Status Change Mail  !! ");
		mailHtmlMessage.setContent("<h2>Hello " + cust.getFirstName() + " " + cust.getLastName()
		        + "! </h2><h3> Your status has been changed to  " + status + " for more details click  "
		        + "<a href=www.printkaari.com/#!/auth/login > here  </a></h3>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSendException("Error occurred while sending CustOrder Status Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent to Customer");

	}
		
	

	private void sendCustomerStatusMailToAdmin(Long customerId, String status, Customer cust) throws InstanceNotFoundException, MailNotSendException {
		try {
			User user = getAdminUser();
			String email = user.getEmailId();
			MailMessage mailHtmlMessage = new MailMessage();
			mailHtmlMessage.setSubject("Customer status mail !! ");
			mailHtmlMessage.setContent("<h2> Hello " + user.getFirstName() + "</h2> <h3>Status of  Customer "
			        + cust.getFirstName() + " " + cust.getLastName() + " Customer ID : "
			        + cust.getId() + " has been changed to  " + status
			        +  " get details   " + " <a href=www.printkaari.com/#!/auth/login >here</a> </h3>");
			mailHtmlMessage.setToAddresses(new String[] { email });

			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSendException("Error occurred while sending order Status Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent to ADMIN");

		
	}

	private Customer getCustomerrByCustOrderId(Long customerId) throws InstanceNotFoundException {
		Customer cust=custDao.find(customerId);
		return cust;
	}

	@Override
	public Map<String, Object> placePurchaseLicenceOrder(GuestForm form) throws DatabaseException {
		Long orderId = null;
		
		CustOrder custOrder = new CustOrder();
		User user = null;
		Customer cust = null;
		Double basePrice = 0.0;
		Double discountAmount = 0.0;
		Double amountToBePaid = 0.0;
		Integer discount = 0;
		Map<String, Object> map = new HashMap<>();
		try {


			/*if (quantity < 2 && quantity >= 0) {
				discount = 15;
			} else if (quantity >= 2) {
				discount = 25;
			} else {

				throw new InvalidQuantiryException("Invalid print quantity supplied ",
				        ErrorCodes.INVALID_PRINT_QUANTITY);
			}*/
			LOGGER.info("Place CustOrder Customer Service Impl");
			/*LOGGER.info("Place CustOrder for Bindig type ::" + bindingType);
			if (bindingType.equalsIgnoreCase("hard")) {
				productCode = ProductCodes.HARD_BINDING.toString();
				basePrice = CostConstant.hard_binnding_base_rate;
			} else if (bindingType.equalsIgnoreCase("spiral")) {
				productCode = ProductCodes.SPIRAL_BINDING.toString();
				basePrice = CostConstant.spiral_binding_base_rate;
			} else {

				LOGGER.info("Invalid Product type for College Projects Catagory");

				throw new InvalidProductException("Invalid Product for College Projects Catagory !",
				        ErrorCodes.INVALID_PRODUCT_ERROR);
			}*/
		//	user = (User) getLoggedinUser();

			if (user != null && user.getUserType().equals(UserTypes.CUSTOMER.toString())) {
				LOGGER.info("Initiate order Logged in user is " + user.getFirstName() + " user id  "
				        + user.getId());
				cust = getCustomerByEmailId(user.getEmailId().trim());
				if (cust != null) {

					LOGGER.info("Initiate order customer is " + cust.getFirstName()
					        + " customer id  " + cust.getId());
					Double totalPrice = basePrice;
					
					System.out.println("totalPrice before round off :" + totalPrice);
					//totalPrice=totalPrice ;
					totalPrice=Math.round(totalPrice*100)/100D;
					

					System.out.println("totalPrice after round off :" + totalPrice);
					//product = fetchProductCodeFromDB(productCode);

					if (form.getMachineId() == null) {

						LOGGER.info("no product found for product code "+totalPrice );
						throw new InvalidMachineIdException(
						        "Invalid machine id ",
						        ErrorCodes.INVALID_MACHINE_ID);
						//LOGGER.debug("");

					} else {
						/*LOGGER.info(
						        "Product found for product code :: " + product.getProductCode());
						LOGGER.info("Product id :: " + product.getId());
						LOGGER.info(
						        "Product sample file id  :: " + product.getSampleFileId().getId());
						LOGGER.info("Product sample file path  :: "
						        + product.getSampleFileId().getFilePath());*/
						discountAmount=totalPrice * (discount / 100.0);
						System.out.println("discount amount before round off :" + discountAmount);
						discountAmount = Math.round(discountAmount*100)/100D;
						System.out.println("discount  :" + discount);
						System.out.println("discount amount after round off :" + discountAmount);
						amountToBePaid = totalPrice - discountAmount;
						System.out.println("Amount to be paid :" + amountToBePaid);

						custOrder.setCustomer(cust);
						custOrder.setDescription(form.getAnyOtherRequest());
						custOrder.setStatus(CommonStatus.INITIATED.toString());
						/*Set<Product> sets = new HashSet<>();
						sets.add(product);*/
						custOrder.setOrderPrice(totalPrice);
						custOrder.setPaidAmount(amountToBePaid);
						custOrder.setDiscountAmount(discountAmount);

						/*Set<CustomerFiles> fileSet = new HashSet<>();

						fileSet.add(custFileDao.find(fileId));*/

						//custOrder.setFileId(fileSet);

						custOrder.setCreatedBy(cust.getFirstName());

						orderId = ordDao.save(custOrder);
						map.put("orderId", orderId);
						map.put("amountToBePaid", custOrder.getPaidAmount());
						map.put("Discount", discountAmount);
						map.put("Amount", custOrder.getPaidAmount() + discountAmount);
						/*map.put("productSample", BASE_UPLOAD_PATH + File.separator
						        + product.getSampleFileId().getFilePath());*/
						/*map.put("productCode", product.getProductCode());
						map.put("productName", product.getName());
						map.put("productId", product.getId());
						map.put("quantity", quantity);*/

						LOGGER.info("OrderDao Save CustOrder --> initiated with CustOrder Id " + orderId);

						sendOrderStatusMailToCustomer(orderId, custOrder.getStatus(), cust);
						sendOrderStatusMailToAdmin(orderId, cust);

					}

				} else {
					LOGGER.info(
					        "Customer object  is null or is not a customer  while placing order ");
				}
			} else {

				LOGGER.info("User is null or is not a customer  while placing order ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while executing placeCollegeOrder in  database", e);
			e.printStackTrace();
			throw new DatabaseException(
			        "Error occured while getting all orders for a customer through database",
			        ErrorCodes.DATABASE_ERROR);
		}
		return map;

		
	}
	
	public String  getDummyLicence() {
		
		Customer cut=new Customer();
		cut.setEmail("test@test.com");
		
		return generateKey("Test", 1 ,"Vsign");
		
	}
}
