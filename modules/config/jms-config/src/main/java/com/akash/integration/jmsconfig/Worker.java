package com.akash.integration.jmsconfig;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer minConsumer;
    private Integer maxConsumer;
}
