package com.akash.e_learniverse_spring_boot.mapper.impl;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballClubDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FootballClubMapperImpl implements CustomObjectMapper<FootballClubEntity, FootballClubDto> {

    // AutoWired hobe ---> "MapperConfig" class ee @Bean hisave Declare kora ase "ModelMapper" ke
    private ModelMapper modelMapper;

    @Autowired
    public FootballClubMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FootballClubDto mapTo(FootballClubEntity footballClubEntity) {
        return modelMapper.map(footballClubEntity, FootballClubDto.class);
    }

    @Override
    public FootballClubEntity mapFrom(FootballClubDto footballClubDto) {
        return modelMapper.map(footballClubDto, FootballClubEntity.class);
    }
}