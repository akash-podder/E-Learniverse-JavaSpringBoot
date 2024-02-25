package com.akash.e_learniverse_spring_boot.service.rating_service;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;

public class RatingSystemService {
    private static void updatePlayerRating(FootballClubEntity currentTeam, FootballClubEntity opponent, boolean win) {
        // Constants for Elo rating calculation
        final int K = 32; // K-factor determines the sensitivity of the rating changes

        // Calculate expected scores
        double expectedScore = 1 / (1 + Math.pow(10, (opponent.getRating() - currentTeam.getRating()) / 400.0));

        // Update player ratings based on match outcome
        int actualScore = win ? 1 : 0;
        currentTeam.setRating((int) (currentTeam.getRating() + K * (actualScore - expectedScore)));
    }

    public static void updateRating(FootballClubEntity currentTeam, FootballClubEntity opponent, boolean currentTeamWin) {
        int opponentRating = opponent.getRating();
        updatePlayerRating(currentTeam, opponent, currentTeamWin);
        updatePlayerRating(opponent, currentTeam, !currentTeamWin);
    }
}
