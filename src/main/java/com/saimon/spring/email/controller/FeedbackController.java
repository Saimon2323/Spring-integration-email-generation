package com.saimon.spring.email.controller;

import com.saimon.spring.email.dto.EmailDto;
import com.saimon.spring.email.entity.EmailConfigEntity;
import com.saimon.spring.email.repository.EmailConfigRepository;
import com.saimon.spring.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailConfigRepository emailConfigRepo;

    /*
    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }
    }
    */

    @RequestMapping("/email")
    public @ResponseBody String sendFeedback() {

        EmailConfigEntity emailConfigEntity = emailConfigRepo.findByEventName("scheduleRefund");

        if (ObjectUtils.isEmpty(emailConfigEntity)){
            throw new EntityNotFoundException("Email Config data not found!");
        }
/*
        String emailFrom = "emarketplace2@gmail.com";
        String[] emailTo = emailConfigEntity.getEmailTo().split(",");
        String replyTo   = emailConfigEntity.getReplyTo();
        String[] emailCc = emailConfigEntity.getEmailCc().split(",");
        String emailSubject = emailConfigEntity.getEmailSubject();
        String emailBody = emailConfigEntity.getEmailBody().replaceAll("#", "\n"); // used for Explicitly Trying to break

        //Attachments : file approach (way 1)
        File attachment1 = new File("/home/saimon/Documents/BRAC_BANK_LTD._24Feb2020.pdf");
        File attachment2 = new File("/home/saimon/Documents/Non_BRAC_Bank_09Mar2020.xls");
        File attachment3 = new File("/home/saimon/Documents/testtxt.csv");

        File[] attachments = new File[] { attachment1, attachment2, attachment3 };


        // attachments (way 2)
        String[] attachFiles = new String[3];
        attachFiles[0]   = "/home/saimon/Documents/Abdur_Rahman's_CV.pdf";
        attachFiles[1] = "/home/saimon/Documents/ticket payment info.png";
        attachFiles[2] = "/home/saimon/Documents/Boibhandar Books.xlsx";
 */
        List<String> attachFiles = new ArrayList<>();
        attachFiles.add("/home/saimon/Documents/Abdur_Rahman's_CV.pdf");
        attachFiles.add("/home/saimon/Documents/Boibhandar Books.xlsx");
        attachFiles.add("/home/saimon/Documents/ticket payment info.png");

        EmailDto emailDto = new EmailDto();
        emailDto.setReplyTo(emailConfigEntity.getReplyTo());
        emailDto.setEmailTo(emailConfigEntity.getEmailTo());
        emailDto.setEmailCc(emailConfigEntity.getEmailCc());
        emailDto.setEmailSubject(emailConfigEntity.getEmailSubject());
        emailDto.setEmailBody(emailConfigEntity.getEmailBody().replaceAll("#", "<br>"));
        emailDto.setAttachFiles(attachFiles);

        boolean sendEmail = emailService.sendEmail(emailDto);

        if (!sendEmail) {
            return "Failed to send Email!";
        }
        return "Email has been sent successfully!";

    }
}
