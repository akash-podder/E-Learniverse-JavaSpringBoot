package com.akash.e_learniverse_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { //this means --> SpringBoot will Scan all the Classes under "com.akash." Package
		"com.akash.*",
})
public class ELearniverseSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(ELearniverseSpringBootApplication.class, args);
	}
}
