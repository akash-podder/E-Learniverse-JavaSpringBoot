package com.akash.e_learniverse_spring_boot.domain.entity;

import com.akash.e_learniverse_spring_boot.security.constant.SecurityEnum;
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
@Table(name = "football_player",
        indexes = {@Index(name = "idx_email",  columnList="email", unique = true)})
public class FootballPlayerEntity {

    @Id //Sequence mane hocche, amader ID field Auto increment hobe... ei author_id_seq amra "DBeaver" (Database er Built-In Sequence) er Sequence eo pabo
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "football_player_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Integer age;

    @Column(name = "jersey_no", length = 100)
    private Integer jerseyNumber;

    private SecurityEnum.FootballPlayerRole role;
}