package com.akash.e_learniverse_spring_boot.pub_sub;
import com.akash.integration.jmsconfig.JmsConstant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EmailConsumer {
    private static final Logger logger = LogManager.getLogger(EmailConsumer.class);

    @RabbitListener(queues = JmsConstant.EMAIL_QUEUE, containerFactory = "e_learniverse_connectionFactory")
    public void publishEmailMessage(String request) {
        publishEmail(request);
    }

    private void publishEmail(String request) {
        logger.info("Email request consumed, request: {}", request);
    }
}
