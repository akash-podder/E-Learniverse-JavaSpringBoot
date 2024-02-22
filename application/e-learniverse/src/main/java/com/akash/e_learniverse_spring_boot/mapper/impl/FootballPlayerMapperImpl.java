package com.akash.e_learniverse_spring_boot.mapper.impl;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FootballPlayerMapperImpl implements CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> {

    // AutoWired hobe ---> "MapperConfig" class ee @Bean hisave Declare kora ase "ModelMapper" ke
    private ModelMapper modelMapper;

    @Autowired
    public FootballPlayerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FootballPlayerDto mapTo(FootballPlayerEntity footballPlayerEntity) {
        return modelMapper.map(footballPlayerEntity, FootballPlayerDto.class);
    }

    @Override
    public FootballPlayerEntity mapFrom(FootballPlayerDto footballPlayerDto) {
        return modelMapper.map(footballPlayerDto, FootballPlayerEntity.class);
    }
}