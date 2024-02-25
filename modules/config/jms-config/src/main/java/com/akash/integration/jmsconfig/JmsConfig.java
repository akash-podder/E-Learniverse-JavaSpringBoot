package com.akash.integration.jmsconfig;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JmsConfig {

    @Value("${e-learniverse.rabbit-config.email-exchange}")
    private String emailExchange;

    @Value("${e-learniverse.rabbit-config.email-queue}")
    private String emailQueue;

    @Value("${e-learniverse.rabbit-config.email-routing-key}")
    private String emailRoutingKey;


    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(JmsConstant.EMAIL_QUEUE).build();
    }

    @Bean
    Exchange emailExchange() {
        return ExchangeBuilder.directExchange(JmsConstant.EMAIL_EXCHANGE).durable(true)
                .build();
    }

    @Bean
    Binding createEmailBinding() {
        return BindingBuilder
                .bind(emailQueue())
                .to(emailExchange())
                .with(JmsConstant.EMAIL_RK)
                .noargs();
    }
}
