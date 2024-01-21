package com.akash.e_learniverse_spring_boot.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/", "/api/home").permitAll() // Allow access to "/" and "/api/home"
//                .antMatchers("/**/*.js", "/**/*.css").permitAll()  //TODO: for CSS/JS MUST Need to add it in Spring Security, otherwise it will be blocked & No css/js file will be loaded
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll() // Use the default "/login" behavior
//                .and()
//                .logout().permitAll();
//    }


    //TODO: for CSS/JS MUST Need to add it in Spring Security, otherwise it will be blocked & No css/js file will be loaded
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/player/**").authenticated() //eikane jei Endpoint gula dibo sudhu sheigular Authentication Lagbe
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();

        httpSecurity.csrf().disable();
    }
}
