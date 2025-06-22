package com.akash.e_learniverse_spring_boot.security.config;

import com.akash.e_learniverse_spring_boot.security.constant.SecurityEnum.*;
import com.akash.e_learniverse_spring_boot.security.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;

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
                //for Api Views
                .antMatchers("/api/create-player").permitAll() //jate kore Player Create korte Authentication Nah lagge
                .antMatchers("/api/**","/api/player/**").authenticated() //eikane jei Endpoint gula dibo sudhu sheigular Authentication Lagbe

                //Normal Views
                .antMatchers("/player/**").authenticated() //eikane jei Endpoint gula dibo sudhu sheigular Authentication Lagbe

                //eikane jei Endpoint gula dibo sudhu sheigular Authentication Lagbe
                .antMatchers("/admin/api/**").hasAnyRole(FootballPlayerRole.ADMIN.getRoleToString(),FootballPlayerRole.MANAGER.getRoleToString(), FootballPlayerRole.CAPTAIN.getRoleToString())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login") // Specify custom login page URL
                    .defaultSuccessUrl("/") // Redirect to "/" successful login
                    .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();

        httpSecurity.csrf().disable();

        //*** Session STATELESS korle ForntEnd ee bar bar Login kora lagbe as Backend Session mone rakhbe Nah ***
        //*** STATELESS korbo jokon JWT use korbo Backend ee ***
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    // this "getPasswordEncoder()" will Create a "PasswordEncoder" object and will be Autowired to Necessary Functions
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
