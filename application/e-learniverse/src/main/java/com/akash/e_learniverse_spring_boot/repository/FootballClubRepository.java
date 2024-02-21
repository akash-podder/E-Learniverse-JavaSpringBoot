package com.akash.e_learniverse_spring_boot.repository;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FootballClubRepository extends JpaRepository<FootballClubEntity, Long> {
    FootballClubEntity findByName(String clubName);
}
