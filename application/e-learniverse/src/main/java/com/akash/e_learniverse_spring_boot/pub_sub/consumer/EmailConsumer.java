package com.akash.e_learniverse_spring_boot.pub_sub.consumer;
import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;
import com.akash.e_learniverse_spring_boot.service.email_service.EmailService;
import com.akash.e_learniverse_spring_boot.util.MapperUtil;
import com.akash.integration.jmsconfig.JmsConstant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private static final Logger logger = LogManager.getLogger(EmailConsumer.class);

    private final EmailService emailService;

    @Autowired
    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = JmsConstant.EMAIL_QUEUE, containerFactory = "e_learniverseSimpleRabbitListenerContainerFactory")
    public void consumeEmailMessage(String request) {
        sendEmailViaEmailService(request);
    }

    private void sendEmailViaEmailService(String request) {
        logger.info("`EmailConsumer` consumed, request: {}", request);

        try {
            SendEmailRequestDto emailRequestDto = MapperUtil.fromJson(request, SendEmailRequestDto.class)
                    .orElseThrow(() -> new Exception("Data cannot be converted to SendEmailRequestDto!"));
            logger.info("`EmailConsumer` converted to Java Object completed, status: {}", emailRequestDto);
            emailService.sendEmail(emailRequestDto);
        }
        catch (Exception ex) {
            logger.error("HandlerOffer failed, cause: {}", ex.getMessage(), ex);
        }
    }
}
