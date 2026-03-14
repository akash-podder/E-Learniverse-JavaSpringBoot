package com.akash.e_learniverse_spring_boot;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


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

    // "footballPlayerService" depends on "footballPlayerRepository" and "footballPlayerMapper"
    // To AUTOMATICALLY insert/Autorwire "MOCK" instance of "footballPlayerRepository" and "footballPlayerMapper" inside "footballPlayerService" we use @InjectMocks
    // @InjectMocks --> it inserts all the Necessary "Dependency Injection's MOCK Object such as footballPlayerRepository and footballPlayerMapper" into "footballPlayerService"
    @InjectMocks
    private FootballPlayerServiceImpl footballPlayerService;

    /**
     * Mock the CustomObjectMapper to avoid actual mapping logic during unit tests
     */
    @Mock
    private CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper;

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
        // Arrange: Create entity and DTO
        FootballPlayerEntity playerEntity = new FootballPlayerEntity(
                1L, "ramos", "ramos@gmail.com", "1234", 33, 4, SecurityEnum.FootballPlayerRole.CAPTAIN);

        FootballPlayerDto playerDto = new FootballPlayerDto(
                1L, "ramos", "ramos@gmail.com", "1234", 33, 4, SecurityEnum.FootballPlayerRole.CAPTAIN);

        // Mock repository response
        when(footballPlayerRepository.findByName("ramos")).thenReturn(playerEntity);

        // Mock mapper response
        when(footballPlayerMapper.mapTo(playerEntity)).thenReturn(playerDto);

        // Act: Call service
        FootballPlayerDto result = footballPlayerService.getFootballPlayerByName("ramos");

        // Assert
        assertNotNull(result);
        assertEquals("ramos", result.getName());
        assertEquals("ramos@gmail.com", result.getEmail());
    }


    // Unit tests for testing "void" return using "doNothing()" and "Mockito.verify()"
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


    // Unit tests for testing "private Method" using "Java Reflections" API
    @Test
    public void testPrivateMethod_validateFootballPlayerName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // to Test Private method we use "Java Reflections" API
        // we first get the Private method
        Method validatePlayerNameMethod = FootballPlayerServiceImpl.class.getDeclaredMethod("validatePlayerName", String.class);

        // and then set the "private" Method's "Access" to "true"... So that we can run variable
        validatePlayerNameMethod.setAccessible(true);

        Boolean isPlayerNameValid = (Boolean) validatePlayerNameMethod.invoke(footballPlayerService, "Ramos");
        assertTrue(isPlayerNameValid);

        Boolean notValid = (Boolean) validatePlayerNameMethod.invoke(footballPlayerService, "123");
        assertFalse(notValid);
    }

    // Unit tests for testing "Java Exceptions"
    @Test
    public void savePlayerShouldThrowExceptionForInvalidPlayerName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Arrange: Create a DTO with invalid player name
        FootballPlayerDto invalidPlayerDto = new FootballPlayerDto(
                1L, "4Ramos3", "ramos@gmail.com", "1234", 33, 4, SecurityEnum.FootballPlayerRole.CAPTAIN);

        // Create corresponding entity with invalid name
        FootballPlayerEntity invalidPlayerEntity = new FootballPlayerEntity(
                1L, "4Ramos3", "ramos@gmail.com", "1234", 33, 4, SecurityEnum.FootballPlayerRole.CAPTAIN);

        // Mock the mapper to convert DTO to Entity
        when(footballPlayerMapper.mapFrom(invalidPlayerDto)).thenReturn(invalidPlayerEntity);

        // Act & Assert
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> footballPlayerService.savePlayer(invalidPlayerDto));

        assertEquals("Player name is invalid", runtimeException.getMessage());
    }
}