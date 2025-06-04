package com.akash.e_learniverse_spring_boot.pub_sub.publisher;

import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;
import com.akash.e_learniverse_spring_boot.util.MapperUtil;
import com.akash.integration.jmsconfig.JmsConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailPublisherImpl implements EmailPublisher {
    private static final Logger logger = LogManager.getLogger(EmailPublisherImpl.class);
    private final RabbitTemplate rabbitTemplate;

    public EmailPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEmailToQueue(SendEmailRequestDto emailRequestDto) {
        logger.info("`EmailPublisher` Publishing message: {}", emailRequestDto);
        try {
            this.rabbitTemplate.convertAndSend(JmsConstant.EMAIL_EXCHANGE,
                    JmsConstant.EMAIL_RK, MapperUtil.toJson(emailRequestDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
