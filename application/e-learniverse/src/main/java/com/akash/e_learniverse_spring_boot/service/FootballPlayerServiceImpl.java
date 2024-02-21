package com.akash.e_learniverse_spring_boot.service;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballPlayerServiceImpl implements FootballPlayerService {
    private static final Logger logger = LogManager.getLogger(FootballPlayerServiceImpl.class);

    private final FootballPlayerRepository footballPlayerRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public FootballPlayerServiceImpl(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    @Override
    public FootballPlayerEntity savePlayer(FootballPlayerEntity footballPlayer) {
        //TODO: we Must ENCODE Password before Saving it to Database
        footballPlayer.setPassword(passwordEncoder.encode(footballPlayer.getPassword()));

        return footballPlayerRepository.save(footballPlayer);
    }

    @Override
    public FootballPlayerEntity getFootballPlayerByName(String playerName) {
        return footballPlayerRepository.findByName(playerName);
    }

    @Override
    public List<FootballPlayerEntity> getAllFootballPlayer() {
        return footballPlayerRepository.findAll();
    }

    @Override
    public FootballPlayerEntity getFootballPlayerByEmail(String email) {
        return footballPlayerRepository.findByEmail(email);
    }
}
