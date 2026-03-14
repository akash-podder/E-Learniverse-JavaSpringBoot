package com.akash.e_learniverse_spring_boot.service.football_player;

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
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final FootballPlayerRepository footballPlayerRepository;

    @Autowired
    public FootballPlayerServiceImpl(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    @Override
    public FootballPlayerEntity savePlayer(FootballPlayerEntity footballPlayer) {
        if(validatePlayerName(footballPlayer.getName())){
            //TODO: we Must ENCODE Password before Saving it to Database
            footballPlayer.setPassword(passwordEncoder.encode(footballPlayer.getPassword()));

            return footballPlayerRepository.save(footballPlayer);
        }
        else{
            throw new RuntimeException("Player name is invalid");
        }
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

    @Override
    public void deleteFootballPlayerByEmail(String email) {
        footballPlayerRepository.deleteByEmail(email);
    }

    // added this "private" Method for Example to test "private" Method in "JUnit and Mockito"
    private boolean validatePlayerName(String playerName) {
        return playerName!=null && !playerName.isEmpty() && playerName.matches("[a-zA-Z]+");
    }
}
