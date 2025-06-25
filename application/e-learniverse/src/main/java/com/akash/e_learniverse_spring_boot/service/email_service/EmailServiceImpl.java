package com.akash.e_learniverse_spring_boot.service.email_service;

import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(SendEmailRequestDto emailRequestDto) {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(emailRequestDto.getTo());
            mail.setSubject(emailRequestDto.getSubject());
            mail.setText(emailRequestDto.getBody());
            javaMailSender.send(mail);
        }
        catch (Exception ex){
            logger.error("Execption while sendEmail: ", ex);
        }
    }
}
