package com.vsign.tech.rest.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.vsign.tech.data.dao.entity.PrintStore;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.message.model.MailMessage;
import com.vsign.tech.message.service.MailService;
import com.vsign.tech.rest.service.TestService;

@RestController
@RequestMapping("/tests")
public class TestController {

	private Logger		LOGGER	= LoggerFactory.getLogger(TestController.class);

	@Autowired
	private MailService	mailService;

	@Autowired
	private TestService	testService;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public String sendTestEmail(WebRequest request, HttpServletResponse response) {
		LOGGER.info(">> sendTestEmail");
		String statusMessage = "";
		String toEmails = request.getParameter("toEmails");
		String[] toEmailArr = null;
		if (toEmails != null && toEmails.trim().length() > 0) {
			if (toEmails.contains(",")) {
				toEmailArr = toEmails.split(",");
			} else {
				toEmailArr = new String[] { toEmails };
			}
		} else {
			toEmailArr = new String[] { "contact@printkaari.com" };
		}
		try {
			LOGGER.info("Sending Text Email");
			MailMessage mailTextMessage = new MailMessage();
			mailTextMessage.setContent("Hi! This is test TEXT email.");
			mailTextMessage.setToAddresses(toEmailArr);
			mailTextMessage.setSubject("Test TEXT Email");
			mailService.sendTextMail(mailTextMessage);
			LOGGER.info("Text Email Sent");
			Long userId = (long) 1001;
			String url = "http://onecore.net/sample-test-cases-for-user-registration-form.htm/"
			        + userId;
			LOGGER.info("Sending HTML sEmail");
			MailMessage mailHtmlMessage = new MailMessage();
			mailHtmlMessage
			        .setContent("<h2>Please click on below link to activate your account</h2>"
			                + " <a href=" + url + ">click here</a>");
			mailHtmlMessage.setToAddresses(toEmailArr);
			mailHtmlMessage.setSubject("Test HTML Email");
			mailService.sendHtmlMail(mailHtmlMessage);
			LOGGER.info("HTML Email Sent");
			statusMessage = "Mail sent successfully to " + Arrays.asList(toEmailArr);
		} catch (Exception e) {
			LOGGER.error("Some error occurred while sending Email.", e);
			statusMessage = e.getMessage();
		}

		LOGGER.info("sendTestEmail <<");
		return statusMessage;
	}

	@RequestMapping(value = "/fetchEntities", method = RequestMethod.GET)
	public List<PrintStore> fetchEntities(WebRequest request, HttpServletResponse response) {
		LOGGER.info(">> fetchEntities");
		List<PrintStore> printStores = testService.fetchAllCompanies();
		LOGGER.info("fetchEntities <<");
		return printStores;
	}

	@RequestMapping(value = "/createEntity", method = RequestMethod.GET)
	public Object createEntity() {
		LOGGER.info(">> createEntity");
		LOGGER.info("createEntity <<");
		return testService.createEntity();
	}

	@RequestMapping(value = "/updateEntity", method = RequestMethod.GET)
	public void updateEntity(@RequestParam Long id) {
		LOGGER.info(">> createEntity");
		try {
			testService.updateEntity(id);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("createEntity <<");
	}

	@Secured(value = "ROLE_COMPANY_ADMIN")
	@RequestMapping(value = "/secured/auth", method = RequestMethod.GET)
	public Object testAuth() {
		LOGGER.info(">> testAuth");
		return "Invoked Successfully!";
	}

}