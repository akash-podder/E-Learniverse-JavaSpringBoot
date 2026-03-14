package com.akash.e_learniverse_spring_boot.service.football_player;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballPlayerServiceImpl implements FootballPlayerService {
    private static final Logger logger = LogManager.getLogger(FootballPlayerServiceImpl.class);
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final FootballPlayerRepository footballPlayerRepository;
    private final CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper;

    @Autowired
    public FootballPlayerServiceImpl(FootballPlayerRepository footballPlayerRepository, CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper) {
        this.footballPlayerRepository = footballPlayerRepository;
        this.footballPlayerMapper = footballPlayerMapper;
    }

    @Override
    public FootballPlayerDto savePlayer(FootballPlayerDto footballPlayerDto) {
        FootballPlayerEntity playerEntity = footballPlayerMapper.mapFrom(footballPlayerDto);

        if(validatePlayerName(playerEntity.getName())){
            //TODO: we Must ENCODE Password before Saving it to Database
            playerEntity.setPassword(passwordEncoder.encode(playerEntity.getPassword()));

            FootballPlayerEntity savedPlayer = footballPlayerRepository.save(playerEntity);
            return footballPlayerMapper.mapTo(savedPlayer);
        }
        else{
            throw new RuntimeException("Player name is invalid");
        }
    }

    @Override
    public FootballPlayerDto getFootballPlayerByName(String playerName) {
        FootballPlayerEntity playerEntity = footballPlayerRepository.findByName(playerName);
        return playerEntity != null ? footballPlayerMapper.mapTo(playerEntity) : null;
    }

    @Override
    public List<FootballPlayerDto> getAllFootballPlayer() {
        return footballPlayerRepository.findAll().stream()
                .map(footballPlayerMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public FootballPlayerDto getFootballPlayerByEmail(String email) {
        FootballPlayerEntity playerEntity = footballPlayerRepository.findByEmail(email);
        return playerEntity != null ? footballPlayerMapper.mapTo(playerEntity) : null;
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
