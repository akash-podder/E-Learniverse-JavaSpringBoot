package com.akash.e_learniverse_spring_boot.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(csrfConfigurer -> csrfConfigurer.disable())
//                .authorizeHttpRequests(
//                        auth -> auth
//                                .antMatchers("/", "/api/home").permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/api/home").permitAll() // Allow access to "/" and "/api/home"
                .anyRequest().authenticated()
                .and()
                .formLogin() // Use the default "/login" behavior
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
