package com.akash.e_learniverse_spring_boot.repository;

import com.akash.e_learniverse_spring_boot.entity.FootballPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FootballPlayerRepository extends JpaRepository<FootballPlayerEntity, Long> {
    FootballPlayerEntity findByName(String playerName);
    FootballPlayerEntity findByEmail(String email);
}
