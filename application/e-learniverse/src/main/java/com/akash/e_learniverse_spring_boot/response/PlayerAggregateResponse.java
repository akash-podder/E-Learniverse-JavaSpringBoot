package com.akash.e_learniverse_spring_boot.response;


import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PlayerAggregateResponse {

    private FootballPlayerDto ramosPlayer;
    private FootballPlayerDto messiPlayer;
    private FootballPlayerDto kroosPlayer;

    public PlayerAggregateResponse(
            FootballPlayerDto ramosPlayer,
            FootballPlayerDto messiPlayer,
            FootballPlayerDto kroosPlayer) {

        this.ramosPlayer = ramosPlayer;
        this.messiPlayer = messiPlayer;
        this.kroosPlayer = kroosPlayer;
    }
}