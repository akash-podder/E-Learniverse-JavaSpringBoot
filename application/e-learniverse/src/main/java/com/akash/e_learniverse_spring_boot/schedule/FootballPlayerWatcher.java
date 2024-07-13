package com.akash.e_learniverse_spring_boot.schedule;

import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FootballPlayerWatcher {
    private static final Logger logger = LogManager.getLogger(FootballPlayerWatcher.class);

    private final FootballPlayerRepository footballPlayerRepository;

    @Autowired
    public FootballPlayerWatcher(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }

    // *** Remember to use "@EnableScheduling" Annotation in "SpringBootApplication's" Main Function ***
    @Scheduled(cron = "* * * * * *")
    public void printFootballPlayerCount() {
        final long playerCount = footballPlayerRepository.count();
        logger.info("There are {} football players", playerCount);
    }
}
