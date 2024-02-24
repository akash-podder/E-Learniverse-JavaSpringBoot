package com.akash.e_learniverse_spring_boot.service.email_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toMail, String emailSubject, String emailBody) {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(toMail);
            mail.setSubject(emailSubject);
            mail.setText(emailBody);
            javaMailSender.send(mail);
        }
        catch (Exception ex){
            logger.error("Execption while sendEmail: ", ex);
        }
    }
}
