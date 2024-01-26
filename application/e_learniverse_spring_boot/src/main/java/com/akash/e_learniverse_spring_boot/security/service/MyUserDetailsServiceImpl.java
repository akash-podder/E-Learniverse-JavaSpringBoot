package com.akash.e_learniverse_spring_boot.security.service;

import com.akash.e_learniverse_spring_boot.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component // We Pass this Service to our Security_Config Class where the Config will know How We Want to Authenticate our users
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final FootballPlayerRepository footballPlayerRepository;

    @Autowired
    public MyUserDetailsServiceImpl(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FootballPlayerEntity footballPlayer = footballPlayerRepository.findByEmail(username); //here, Email is the Unique identifer for EveryFootball Player

        if (footballPlayer != null) {
            UserDetails userDetails = User.builder()
                    .username(footballPlayer.getEmail())
                    .password(footballPlayer.getPassword())
                    .roles(footballPlayer.getRole())
                    .build();

            return userDetails;
        }

        throw new UsernameNotFoundException("Football Player User Not found with Email: " + username);
    }
}
