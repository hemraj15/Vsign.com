package com.vsign.tech.message.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.vsign.tech.message.exception.MailNotSentException;
import com.vsign.tech.message.model.MailMessage;
import com.vsign.tech.message.utils.ReadConfigurationFile;

@Component
public class MailServiceImpl implements MailService {

	private Logger			LOGGER	= LoggerFactory.getLogger(MailServiceImpl.class);

	private static String	defaultFromAddress;

	static {
		defaultFromAddress = ReadConfigurationFile.getProperties("mail.properties")
		        .getProperty("mail.default.from");
	}

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public void sendTextMail(MailMessage mailMessage) throws MailNotSentException {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mailMessage.getToAddresses());
			message.setFrom((mailMessage.getFromAddress() != null
			        && mailMessage.getFromAddress().trim().length() > 0)
			                ? mailMessage.getFromAddress() : defaultFromAddress);
			message.setSubject(mailMessage.getSubject());
			message.setText(mailMessage.getContent().toString());
			mailSender.send(message);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSentException(e.getMessage(), e);
		}
	}

	@Override
	public void sendHtmlMail(MailMessage mailMessage) throws MailNotSentException {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
			message.setContent(mailMessage.getContent(), "text/html");
			helper.setTo(mailMessage.getToAddresses());
			helper.setSubject(mailMessage.getSubject());
			helper.setFrom((mailMessage.getFromAddress() != null
			        && mailMessage.getFromAddress().trim().length() > 0)
			                ? mailMessage.getFromAddress() : defaultFromAddress);
			mailSender.send(message);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MailNotSentException(e.getMessage(), e);
		}
	}
}
