//package com.saimon.spring.email.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Objects;
//import java.util.Properties;
//
//// EmailConfig are already set in Application.properties file. So no need to set it here again.
//// Either set in Application.properties file or set it like this by creating a Config class.
//@Configuration
//public class EmailConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private int port;
//    // javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory") dile mane ssl on korle tokhon port 465 dite hobe
//    // or port 465 dile, uporer ta mane ssl er line ta dite hobe
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Bean
//    public JavaMailSender javaMailSender(){
//        //create a mail sender
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost(host); // or javaMailSender.setHost(env.getProperty("spring.mail.host"));
//        javaMailSender.setPort(port); // or javaMailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
//        javaMailSender.setUsername(username);
//        javaMailSender.setPassword(password);
//
//        //Properties javaMailProperties = new Properties(); //evabe dile 'javaMailSender.setJavaMailProperties(javaMailProperties);' line o dite hobe
//        Properties javaMailProperties = javaMailSender.getJavaMailProperties(); //evabe dile 'javaMailSender.setJavaMailProperties(javaMailProperties);' line dewar dorkar nai
//        javaMailProperties.put("mail.smtp.starttls.enable", "true"); // required if port is 587
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        //javaMailProperties.put("mail.debug", "true"); // Indicates that the DEBUG mode is turned on, so that the log of the email sending process will be printed on the console to facilitate troubleshooting
//        //javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//        //javaMailSender.setJavaMailProperties(javaMailProperties);
//
//        return javaMailSender;
//    }
//
//
//}
