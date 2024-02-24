package com.akash.e_learniverse_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.akash.*",
})
public class ELearniverseSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(ELearniverseSpringBootApplication.class, args);
	}
}
