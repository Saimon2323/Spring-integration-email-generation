package com.saimon.spring.email.dto;

import java.util.List;

public class EmailDto {

    private String replyTo;
    private String emailTo;
    private String emailCc;
    private String emailSubject;
    private String emailBody;
    private List<String> attachFiles;

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailCc() {
        return emailCc;
    }

    public void setEmailCc(String emailCc) {
        this.emailCc = emailCc;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public List<String> getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(List<String> attachFiles) {
        this.attachFiles = attachFiles;
    }

    @Override
    public String toString() {
        return "EmailDto{" +
                "replyTo='" + replyTo + '\'' +
                ", emailTo='" + emailTo + '\'' +
                ", emailCc='" + emailCc + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                ", attachFiles=" + attachFiles +
                '}';
    }
}
