package com.vsign.tech.message.model;

import java.util.Arrays;

public class MailMessage {

    private String[] toAddresses;
    private String   fromAddress;
    private String   subject;
    private Object   content;

    public String[] getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(String[] toAddresses) {
        this.toAddresses = toAddresses;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MailMessage [toAddresses=");
        builder.append(Arrays.toString(toAddresses));
        builder.append(", fromAddress=");
        builder.append(fromAddress);
        builder.append(", subject=");
        builder.append(subject);
        builder.append(", content=");
        builder.append(content);
        builder.append("]");
        return builder.toString();
    }

}
