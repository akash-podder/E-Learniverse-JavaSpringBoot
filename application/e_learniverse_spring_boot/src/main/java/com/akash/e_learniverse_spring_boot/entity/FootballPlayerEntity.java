package com.akash.e_learniverse_spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "football_player")
public class FootballPlayerEntity {

    @Id //Sequence mane hocche, amader ID field Auto increment hobe... ei author_id_seq amra DBVear er Sequence eo pabo
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "football_player_id_seq")
    private Long id;

    private String name;

    private String password;

    private Integer age;

    @JsonProperty("jersey_no") //Json format ee jerseyNumber takbe "jersey_no" hisave
    private Integer jerseyNumber;
}