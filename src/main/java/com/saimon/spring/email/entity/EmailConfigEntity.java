package com.saimon.spring.email.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "email_config")
public class EmailConfigEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Size(max = 500)
    @Column(name = "reply_to")
    private String replyTo;

    @Size(max = 500)
    @Column(name = "email_to", nullable = false)
    private String emailTo;

    @Size(max = 500)
    @Column(name = "email_cc")
    private String emailCc;

    @Size(max = 500)
    @Column(name = "email_subject", nullable = false)
    private String emailSubject;

    @Lob
    @Column(name = "email_body", nullable = false)
    private String emailBody;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

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

}
