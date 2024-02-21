package com.akash.e_learniverse_spring_boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	// The purpose of SpringBootServletInitializer is to configure your Spring Boot application's ApplicationContext when it is deployed as a WAR file.
	// It extends WebApplicationInitializer and provides a convenient way to configure the servlet container programmatically using Spring Boot's features.

	// When you deploy a Spring Boot application as a WAR file into an external servlet container, the servlet container needs to be initialized properly.
	// SpringBootServletInitializer configures the servlet container programmatically, ensuring that Spring Boot's features are properly integrated with the servlet container's lifecycle.

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ELearniverseSpringBootApplication.class);
	}
}
