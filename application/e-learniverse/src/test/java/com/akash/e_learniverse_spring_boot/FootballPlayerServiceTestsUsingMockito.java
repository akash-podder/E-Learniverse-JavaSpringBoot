package com.akash.e_learniverse_spring_boot;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import com.akash.e_learniverse_spring_boot.security.constant.SecurityEnum;
import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerService;

import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FootballPlayerServiceTestsUsingMockito {

    /**
     * "Mockito" is mainly used for Mocking `Database` Calls... Here, FootballPlayerServiceImpl --> FootballPlayerRepository --> calling "Database"
     * as such we are Mocking the "FootballPlayerRepository" so that it doesn't call the "Database"
     */

    @InjectMocks
    private FootballPlayerServiceImpl footballPlayerService;

    @Mock
    private FootballPlayerRepository footballPlayerRepository;

    @Before
    public void setUp() {
        // MockitoJUnitRunner handles mock initialization, so this is optional
    }

    @Test
    public void getFootballPlayerByNameTest() {
        // Mock repository response
        when(footballPlayerRepository.findByName(anyString()))
                .thenReturn(new FootballPlayerEntity(
                        1L, "ramos", "ramos@gmail.com", "1234", 33, 4, SecurityEnum.FootballPlayerRole.CAPTAIN));

        // Call service
        FootballPlayerEntity result = footballPlayerService.getFootballPlayerByName("ramos");

        // Assert
        assertNotNull(result);
        assertEquals("ramos", result.getName());
    }
}