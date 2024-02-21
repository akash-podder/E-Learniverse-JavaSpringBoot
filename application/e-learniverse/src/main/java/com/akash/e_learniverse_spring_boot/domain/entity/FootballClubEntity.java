package com.akash.e_learniverse_spring_boot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "football_club",
        indexes = {@Index(name = "idx_name",  columnList="name", unique = true)})
public class FootballClubEntity {

    @Id //Sequence mane hocche, amader ID field Auto increment hobe... ei football_club_id_seq amra DBVear er Sequence eo pabo
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "football_club_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
}