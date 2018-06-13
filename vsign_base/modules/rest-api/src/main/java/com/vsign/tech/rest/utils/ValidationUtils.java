package com.vsign.tech.rest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationUtils {
    private static Logger      LOGGER        = LoggerFactory
                                                     .getLogger(ValidationUtils.class);

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                     + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate email with regular expression
     * 
     * @param email
     *            email for validation
     * @return true valid email, false invalid email
     */
    public static boolean validateEmail(String email) {
        LOGGER.debug(">> validate");
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        LOGGER.debug(" Is Email valid " + matcher.matches());
        LOGGER.debug("validate <<");
        return matcher.matches();
    }
}
