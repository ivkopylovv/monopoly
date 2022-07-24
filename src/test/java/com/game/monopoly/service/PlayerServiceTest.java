package com.game.monopoly.service;

import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.PlayerRole;
import com.game.monopoly.enums.PlayerStatus;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;

import static com.game.monopoly.constants.InitialGameValue.*;
import static com.game.monopoly.enums.PlayerColour.GREEN;
import static com.game.monopoly.enums.PlayerRole.USER;
import static com.game.monopoly.enums.PlayerStatus.LOST;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
class PlayerServiceTest {
    public static final String SESSION_ID = "123";
    public static final long PLAYER_ID = 10L;
    public static final String ACTUAL_NAME = "Masha";
    public static final String ACTUAL_COLOUR = "GREEN";
    public static final PlayerRole PLAYER_ROLE = USER;

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private PlayerDAO playerDAO;

    private PlayerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PlayerServiceImpl(playerDAO);
        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS);
        sessionDAO.save(session);
        Player player = new Player()
                .setId(PLAYER_ID)
                .setUniqueName(new PlayerUniqueName(SESSION_ID, ACTUAL_NAME))
                .setPosition(INITIAL_PLAYER_POSITION)
                .setColour(GREEN)
                .setRole(PLAYER_ROLE);
        playerDAO.save(player);
    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
        playerDAO.deleteAll();
    }

    @Test
    void canGetPlayer() {
        Player expectedPlayer = playerDAO.findPlayerByUniqueName(new PlayerUniqueName(SESSION_ID, ACTUAL_NAME)).get();
        assertEquals(expectedPlayer, underTest.getPlayer(SESSION_ID, ACTUAL_NAME));
    }

    @Test
    void itShouldCatchResourceNotFoundException() {
        String newPlayerName = "Vanya";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            underTest.getPlayer(SESSION_ID, newPlayerName);
        });
        Assertions.assertEquals("Player Not Found", thrown.getMessage());
    }

    @Test
    void canSavePlayer() {
        String newPlayerName = "Vlad";
        Player expectedPlayer = new Player()
                .setUniqueName(new PlayerUniqueName(SESSION_ID, newPlayerName))
                .setPosition(INITIAL_PLAYER_POSITION)
                .setColour(GREEN)
                .setRole(PLAYER_ROLE);
        underTest.savePlayer(SESSION_ID, newPlayerName, ACTUAL_COLOUR, PLAYER_ROLE);
        assertEquals(expectedPlayer.getUniqueName(),
                playerDAO.findPlayerByUniqueName(
                        new PlayerUniqueName(SESSION_ID, newPlayerName)).get().getUniqueName());
    }

    @Test
    void itShouldUpdatePlayerPosition() {
        int expectedPosition = 1;
        underTest.updatePlayerPosition(1, SESSION_ID, ACTUAL_NAME);
        int newPosition = playerDAO.findPlayerByUniqueName(
                new PlayerUniqueName(SESSION_ID, ACTUAL_NAME))
                .get()
                .getPosition();
        assertNotNull(newPosition);
        assertEquals(expectedPosition, newPosition);
    }

    @Test
    void itShouldUpdatePlayerBalance() {
        Long expectedBalance = 2000L;
        underTest.updatePlayerBalance(2000L, SESSION_ID, ACTUAL_NAME);
        Long newBalance = playerDAO.findPlayerByUniqueName(
                        new PlayerUniqueName(SESSION_ID, ACTUAL_NAME))
                .get()
                .getBalance();
        assertNotNull(newBalance);
        assertEquals(expectedBalance, newBalance);
    }

    @Test
    void itShouldUpdatePlayerStatus() {
        PlayerStatus expectedPlayerStatus = LOST;
        underTest.updatePlayerStatus(LOST, SESSION_ID, ACTUAL_NAME);
        PlayerStatus newPlayerStatus = playerDAO.findPlayerByUniqueName(
                        new PlayerUniqueName(SESSION_ID, ACTUAL_NAME))
                .get()
                .getStatus();
        assertNotNull(newPlayerStatus);
        assertEquals(expectedPlayerStatus, newPlayerStatus);
    }

    @Test
    void itShouldCatchDataIntegrityViolationExceptionWhenUpdatePlayerBalance() {
        Long Balance = null;
        DataIntegrityViolationException thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            underTest.updatePlayerBalance(Balance, SESSION_ID, ACTUAL_NAME);
        });
        assertTrue(thrown.getMessage().contains("null"));
    }

}