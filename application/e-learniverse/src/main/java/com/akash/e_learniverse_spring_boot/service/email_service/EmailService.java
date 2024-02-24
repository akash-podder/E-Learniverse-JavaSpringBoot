package com.akash.e_learniverse_spring_boot.service.email_service;

public interface EmailService {

    void sendEmail(String toMail, String emailSubject, String emailBody);
}
