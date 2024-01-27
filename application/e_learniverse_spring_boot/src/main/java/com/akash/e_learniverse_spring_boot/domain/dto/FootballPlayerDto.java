package com.akash.e_learniverse_spring_boot.domain.dto;

import com.akash.e_learniverse_spring_boot.security.constant.SecurityEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballPlayerDto {
    //TODO: Controller Only "FootballPlayerDto" Request hisave Nibe & Response hisave Dibe...
    // Controller(Presentaion Layer) Should have No Idea about "FootballPlayerEntity"... ei karon DTO(Data Transfer Object) use kora huise "FootballPlayerEntity" theke "FootballPlayerDto" te nite
    // Persistence Layer(Repository) & Service Layer should access "FootballPlayerEntity"

    private Long id;

    private String name;

    private String email;

    private String password;

    private Integer age;

    @JsonProperty("jersey_no")
    private Integer jerseyNumber;

    private SecurityEnum.FootballPlayerRole role;
}
