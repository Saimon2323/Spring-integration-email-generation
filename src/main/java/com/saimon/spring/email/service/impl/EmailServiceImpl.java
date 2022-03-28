package com.saimon.spring.email.service.impl;

import com.saimon.spring.email.dto.EmailDto;
import com.saimon.spring.email.service.EmailService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

//    https://programmer.ink/think/full-analysis-of-spring-boot-email.html or https://laptrinhx.com/full-analysis-of-spring-boot-email-1062414205/
//    https://www.baeldung.com/spring-email
//    https://mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/
//    https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch26s03.html
//    https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/MimeMessageHelper.html
//    http://springboot.javaboy.org/2019/0717/springboot-mail
    @Override
    public boolean sendEmail(EmailDto emailDto) {

        logger.info("Sending Email with EmailDto : {}", emailDto);

        if (StringUtils.isEmpty(emailDto.getEmailTo())) {
            logger.error("EmailTo must not be Empty. EmailTo : {}", emailDto.getEmailTo());
            return false;
        }

        //create a email instance without attachment
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(emailFrom);
//        mailMessage.setTo("saimon.ctg@gmail.com", "abidhasan.dev@gmail.com", "shakib.brac@gmail.com");
//        mailMessage.setReplyTo("saimon.ctg@gmail.com");
//        mailMessage.setCc("shakib.brac@gmail.com", "mdsaimon.dev@gmail.com");
//        mailMessage.setSubject("New feedback. " + new Date() + "!");
//        mailMessage.setText(emailDto.getEmailBody());
//        javaMailSender.send(mailMessage);

        //sent with attachment
        //Please note whether the mail size is limited by the from and to mail servers
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();

            // Multipart email messages allow for both attachments and inline resources. Examples of inline resources would be be images
            // or a stylesheet you want to use in your message, but that you don't want displayed as an attachment.
            // use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            setSendingMailData(emailDto, helper);

            //send Email
            javaMailSender.send(mailMessage);

            logger.info("Email sent successfully to [{}] ", emailDto.getEmailTo());
//            logger.info("Email sent successfully to " + Arrays.toString(emailTo));

            return true;

        } catch (MessagingException e) {
            logger.error("Could not send email : {}", e.getMessage(), e);
            return false;
        }

    }

    private void setSendingMailData(EmailDto emailDto, MimeMessageHelper helper) throws MessagingException {

        helper.setFrom(emailFrom); // Optional

        //String[] toAddresses = {"saimon.ctg@gmail.com", "ahsaimon.ctg@gmail.com"};
        helper.setTo(InternetAddress.parse(emailDto.getEmailTo().trim(), false));

        if (!StringUtils.isEmpty(emailDto.getReplyTo())) {
            helper.setReplyTo(emailDto.getReplyTo().trim());
        }

        if (!StringUtils.isEmpty(emailDto.getEmailCc())) {
            //String[] ccAddresses = {"abidsaimon2323@gmail.com", "storm.ctg@gmail.com"};
            helper.setCc(InternetAddress.parse(emailDto.getEmailCc().trim(), false));
        }

        if (!StringUtils.isEmpty(emailDto.getEmailSubject())) {
            helper.setSubject(emailDto.getEmailSubject().trim());
        }

        if (StringUtils.isEmpty(emailDto.getEmailBody())) {
            helper.setText(Strings.EMPTY);
        } else {
            helper.setText(emailDto.getEmailBody(), true);
//          helper.setText("<html><body>" + emailDto.getEmailBody() + "</body></html>", true); jodi HTML a organize na thake
        }

        helper.setSentDate(new Date());

//        for (File file: attachments) {
//            FileSystemResource fileSystemResource = new FileSystemResource(file);
//            helper.addAttachment(file.getName(), fileSystemResource);
//        }

        if (!CollectionUtils.isEmpty(emailDto.getAttachFiles())) {
            for (String filepath : emailDto.getAttachFiles()) {
                File file = new File(filepath);
                helper.addAttachment(file.getName(), file);
                logger.info("sending Mail With Attached File : {}", file.getName());
            }
        }
    }

}
