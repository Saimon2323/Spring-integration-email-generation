package com.saimon.spring.email.service.impl;

import com.saimon.spring.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String emailFrom, String[] emailTo, String replyTo, String[] emailCc,
                          String emailSubject, String emailBody, String[] attachFiles) throws MessagingException {

        //create a email instance without attachment
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(emailConfigEntity.getEmail());
//        mailMessage.setTo("saimon.ctg@gmail.com", "ahsaimon.ctg@gmail.com", "shakib.brac@gmail.com");
//        mailMessage.setReplyTo("saimon.ctg@gmail.com");
//        mailMessage.setCc("abidsaimon2323@gmail.com", "storm.ctg@gmail.com");
//        mailMessage.setSubject("New feedback from "+emailConfigEntity.getName()+"!");
//        mailMessage.setText(emailConfigEntity.getFeedback());

        //sent with attachment
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

        helper.setFrom(emailFrom);

        //String[] toAddresses = {"saimon.ctg@gmail.com", "ahsaimon.ctg@gmail.com"};
        helper.setTo(emailTo);

        helper.setReplyTo(replyTo);

        //String[] ccAddresses = {"abidsaimon2323@gmail.com", "storm.ctg@gmail.com"};
        helper.setCc(emailCc);

        helper.setSubject(emailSubject);
        helper.setText(emailBody);

//        for (File file: attachments) {
//            FileSystemResource fileSystemResource = new FileSystemResource(file);
//            helper.addAttachment(file.getName(), fileSystemResource);
//        }

        for (String filepath:attachFiles){
            File file = new File(filepath);
            helper.addAttachment(file.getName(), file);
        }

        //send Email
        mailSender.send(mailMessage);

        logger.info("Email sent successfully to "+ Arrays.toString(emailTo));

    }

}
