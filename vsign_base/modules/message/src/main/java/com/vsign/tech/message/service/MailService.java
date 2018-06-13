package com.vsign.tech.message.service;

import com.vsign.tech.message.exception.MailNotSentException;
import com.vsign.tech.message.model.MailMessage;

public interface MailService {

    void sendTextMail(MailMessage mailMessage) throws MailNotSentException;

    void sendHtmlMail(MailMessage mailMessage) throws MailNotSentException;

}
