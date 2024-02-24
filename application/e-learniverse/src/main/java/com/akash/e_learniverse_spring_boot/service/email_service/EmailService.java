package com.akash.e_learniverse_spring_boot.service.email_service;

import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;

public interface EmailService {

    void sendEmail(SendEmailRequestDto emailRequestDto);
}
