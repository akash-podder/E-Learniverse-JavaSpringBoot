package com.akash.e_learniverse_spring_boot.pub_sub.publisher;
import com.akash.integration.jmsconfig.JmsConstant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private static final Logger logger = LogManager.getLogger(EmailConsumer.class);

    @RabbitListener(queues = JmsConstant.EMAIL_QUEUE, containerFactory = "e_learniverseSimpleRabbitListenerContainerFactory")
    public void publishEmailMessage(String request) {
        publishEmail(request);
    }

    private void publishEmail(String request) {
        logger.info("Email request consumed, request: {}", request);
    }
}
