package com.akash.integration.jmsconfig;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("e-learniverse.jms")
@Configuration
@EnableConfigurationProperties
public class JmsProperties implements Serializable {

    private static final long serialVersionUID = 1L;

    private String host;
    private Integer port;
    private String user;
    private String password;
    private Integer minConsumer;
    private Integer maxConsumer;
    private Boolean waitForPublisherResponse;
}
