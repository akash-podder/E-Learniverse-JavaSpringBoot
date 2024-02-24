package com.akash.e_learniverse_spring_boot.pub_sub.consumer;

import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;
import com.akash.integration.jmsconfig.JmsConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailPublisher {
    private static final Logger logger = LogManager.getLogger(EmailPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public EmailPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(SendEmailRequestDto emailRequestDto) {
        this.rabbitTemplate.convertAndSend(JmsConstant.EMAIL_EXCHANGE,
                JmsConstant.EMAIL_RK, "Hello Ramos");
    }
}
