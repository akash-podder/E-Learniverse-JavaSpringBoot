package com.akash.e_learniverse_spring_boot.service;

import com.akash.e_learniverse_spring_boot.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballPlayerServiceImpl implements FootballPlayerService {
    private static final Logger logger = LogManager.getLogger(FootballPlayerServiceImpl.class);

    private final FootballPlayerRepository footballPlayerRepository;

    @Autowired
    public FootballPlayerServiceImpl(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    @Override
    public FootballPlayerEntity save_player(FootballPlayerEntity footballPlayer) {
        return footballPlayerRepository.save(footballPlayer);
    }

    @Override
    public List<FootballPlayerEntity> get_all_football_player() {
        return footballPlayerRepository.findAll();
    }
}
