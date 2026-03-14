package com.akash.e_learniverse_spring_boot;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import com.akash.e_learniverse_spring_boot.security.constant.SecurityEnum;
import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * So that this class can create mock we have to mark this class with
 * "@RunWith(MockitoJUnitRunner.class)"
 */
@RunWith(MockitoJUnitRunner.class)
public class FootballPlayerServiceTestsUsingMockito {

    /**
     * "Mockito" is mainly used for Mocking `Database` Calls... Here, "FootballPlayerService" calls --> "FootballPlayerRepository" --> calling "Database"
     * as such we are Mocking the "FootballPlayerRepository" so that it doesn't call the "Database"
     */
    @Mock
    private FootballPlayerRepository footballPlayerRepository;

    // "footballPlayerService" depends on "footballPlayerRepository"
    // To AUTOMATICALLY insert/Autorwire "MOCK" instance of "footballPlayerRepository" inside "footballPlayerService" we use @InjectMocks
    // @InjectMocks --> it inserts all the Necessary "Dependency Injection's MOCK Object such as footballPlayerRepository" into "footballPlayerService"
    @InjectMocks
    private FootballPlayerServiceImpl footballPlayerService;

    @Before
    public void setUp() {
        // MockitoJUnitRunner handles mock initialization, so this is optional
    }

    // this has to be "static" method
    @BeforeAll
    public static void init() {
        System.out.println("BeforeAll");
    }

    // this NOT "static" method
    @BeforeEach
    public void initEachTest() {
        System.out.println("BeforeEach");
    }

    @Test
    public void getFootballPlayerByNameTest() {
        // Mock repository response
        // this means, while calling "footballPlayerService.getFootballPlayerByName()" and we encounter any line that calls "footballPlayerRepository.findByName()", then Return this DUMMY Response
        when(footballPlayerRepository.findByName(anyString()))
                .thenReturn(new FootballPlayerEntity(
                        1L, "ramos", "ramos@gmail.com", "1234", 33, 4, SecurityEnum.FootballPlayerRole.CAPTAIN));

        // Call service
        FootballPlayerEntity result = footballPlayerService.getFootballPlayerByName("ramos");

        // Assert
        assertNotNull(result);
        assertEquals("ramos", result.getName());
    }

    @Test
    public void deleteFootballPlayerByEmailSuccessfullyTest() {
        // this means, while calling "footballPlayerService.deleteFootballPlayerByEmail()" and we encounter any line that calls "footballPlayerRepository.deleteByEmail()"
        // then we "Do Nothing" as "footballPlayerRepository.deleteByEmail()" the statement returns "void"
        doNothing().when(footballPlayerRepository).deleteByEmail(anyString());

        // as "deleteFootballPlayerByEmail" Returns "void" to check whether this has called "footballPlayerRepository.deleteByEmail()" Inside
        // we have to use "Mockito.verify" and check INTERNALLY:
        // footballPlayerService.deleteFootballPlayerByEmail() ---> calls --> footballPlayerRepository.deleteByEmail() Method;
        footballPlayerService.deleteFootballPlayerByEmail("ramos@gmail.com");
        Mockito.verify(footballPlayerRepository, Mockito.times(1)).deleteByEmail("ramos@gmail.com");
    }
}