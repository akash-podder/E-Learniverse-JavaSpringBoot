package com.akash.e_learniverse_spring_boot.domain.entity.one_to_many;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    private String countryName;

    @OneToMany(mappedBy = "countryX", cascade = CascadeType.ALL) // CITY table ee giye Country er FOREIGN_KEY SAVE hobe
    private List<City> cityList = new ArrayList<>();
}