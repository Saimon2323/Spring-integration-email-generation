package com.saimon.spring.email.service;

import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

@Service
public interface EmailService {

    public void sendEmail(String emailFrom, String[] emailTo, String replyTo, String[] emailCc,
                          String emailSubject, String emailBody, String[] attachFiles) throws MessagingException;
}
