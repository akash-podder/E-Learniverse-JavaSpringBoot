package com.akash.integration.jmsconfig;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConsumerConfig {

    @Bean("e_learniverse_connectionFactory")
    public SimpleRabbitListenerContainerFactory eLearniverseConnectionFactory(
            @Qualifier("learniverseConnectionFactory") ConnectionFactory connectionFactory,
             JmsProperties jmsProperties)
    {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(jmsProperties.getMinConsumer());
        factory.setMaxConcurrentConsumers(jmsProperties.getMaxConsumer());
        return factory;
    }
}
