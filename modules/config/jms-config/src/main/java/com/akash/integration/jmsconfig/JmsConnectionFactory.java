package com.akash.integration.jmsconfig;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class JmsConnectionFactory {

    @Bean("learniverseConnectionFactory")
    ConnectionFactory connectionFactory(JmsProperties jmsProperties) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(jmsProperties.getHost());

        connectionFactory.setUsername(jmsProperties.getUser());
        connectionFactory.setPassword(jmsProperties.getPassword());
        connectionFactory.setPort(jmsProperties.getPort());
        if (jmsProperties.getWaitForPublisherResponse()) {
            connectionFactory.setPublisherReturns(true);
        }

        return connectionFactory;
    }
}
