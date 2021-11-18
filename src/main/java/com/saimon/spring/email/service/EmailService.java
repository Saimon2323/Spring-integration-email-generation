package com.saimon.spring.email.service;

import com.saimon.spring.email.dto.EmailDto;

import javax.mail.MessagingException;

public interface EmailService {

//    public void sendEmail(String emailFrom, String[] emailTo, String replyTo, String[] emailCc,
//                          String emailSubject, String emailBody, String[] attachFiles);
    boolean sendEmail(EmailDto emailDto);
}
