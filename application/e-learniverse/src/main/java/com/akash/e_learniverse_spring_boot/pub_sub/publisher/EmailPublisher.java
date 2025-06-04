package com.akash.e_learniverse_spring_boot.pub_sub.publisher;

import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;

public interface EmailPublisher {
    void publishEmailToQueue(SendEmailRequestDto emailRequestDto);
}
