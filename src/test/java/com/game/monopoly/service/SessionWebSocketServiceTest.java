package com.game.monopoly.service;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.dao.MessageDAO;
import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.enums.PlayerRole;
import com.game.monopoly.service.impl.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;
import static com.game.monopoly.enums.MoveStatus.START;
import static com.game.monopoly.enums.PlayerRole.USER;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
class SessionWebSocketServiceTest {

    public static final String SESSION_ID = "222";
    public static final long PLAYER_ID = 10L;
    public static final Integer ACTUAL_POSITION = 0;
    public static final PlayerRole PLAYER_ROLE = USER;

    public static final Long ACTUAL_BALANCE = 1000L;

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    MessageDAO messageDAO;
    @Autowired
    CardStateDAO cardStateDAO;

    private ChatService chatService;
    private SessionCommonService sessionCommonService;
    private PlayerService playerService;
    private CardStateService cardStateService;

    private SessionWebSocketService underTest;

    @BeforeEach
    void setUp() {
        sessionCommonService = new SessionCommonServiceImpl(sessionDAO);
        chatService = new ChatServiceImpl(messageDAO, sessionCommonService);
        playerService = new PlayerServiceImpl(playerDAO);
        cardStateService = new CardStateServiceImpl(cardStateDAO);
        underTest = new SessionWebSocketServiceImpl(messageDAO, sessionDAO,
                chatService, sessionCommonService, playerService, cardStateService);
        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(START);
        sessionDAO.save(session);
    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
    }

    @Test
    void canAddPlayerToSession() {
        Player player = new Player()
                .setId(PLAYER_ID)
                .setUniqueName(new PlayerUniqueName(SESSION_ID, "Danila"))
                .setPosition(ACTUAL_POSITION)
                .setRole(PLAYER_ROLE)
                .setColour(PlayerColour.valueOf(String.valueOf("RED")))
                .setBalance(ACTUAL_BALANCE);
        underTest.addPlayerToSession(SESSION_ID, "Danila", "RED");
        assertEquals(player.getUniqueName(), sessionDAO.findById(SESSION_ID).get().getPlayers().get(0).getUniqueName());
    }

    @RepeatedTest(10)
    void canGetCorrectRollDices() {
        String newName = "Nikita";
        underTest.addPlayerToSession(SESSION_ID, newName, "BLUE");
        RollDiceResultDTO rollDiceResultDTO = underTest.rollDices(SESSION_ID, newName);
        int firstDigit = rollDiceResultDTO.getDigits().get(0);
        int secondDigit = rollDiceResultDTO.getDigits().get(1);
        int sumDigits = firstDigit + secondDigit;
        boolean correctBorders = (sumDigits > 1) && (sumDigits < 13);
        boolean correctFirstDigit = (firstDigit > 0) && (firstDigit < 7);
        boolean correctSecondDigit = (secondDigit > 0) && (secondDigit < 7);
        assertTrue(correctBorders);
        assertTrue(correctFirstDigit);
        assertTrue(correctSecondDigit);
    }

    @Test
    void canGetNotNullRollDices() {
        String newName = "Nikita";
        underTest.addPlayerToSession(SESSION_ID, newName, "BLUE");
        RollDiceResultDTO rollDiceResultDTO = underTest.rollDices(SESSION_ID, newName);
        assertNotNull(rollDiceResultDTO.getDigits());
    }

    @Test
    void canGetNextPlayer() {
        String previousPLayer = "Bob";
        String nextPlayer = "Kate";
        underTest.addPlayerToSession(SESSION_ID, previousPLayer, "GREEN");
        underTest.addPlayerToSession(SESSION_ID, nextPlayer, "YELLOW");
        String nextPlayerName = underTest.getNextPlayer(SESSION_ID, previousPLayer);
        assertEquals(nextPlayer, nextPlayerName);
    }

    @Test
    void canGetNotNullNextPlayer() {
        String previousPLayer = "Bob";
        String nextPlayer = "Kate";
        underTest.addPlayerToSession(SESSION_ID, previousPLayer, "GREEN");
        underTest.addPlayerToSession(SESSION_ID, nextPlayer, "YELLOW");
        String nextPlayerName = underTest.getNextPlayer(SESSION_ID, previousPLayer);
        assertNotNull(nextPlayerName);
    }

    @Test
    void canGetCurrentMoveStatus() {
        MoveStatus moveStatus = START;
        MoveStatus expectedMoveStatus = underTest.getCurrentMoveStatus(SESSION_ID);
        assertEquals(moveStatus, expectedMoveStatus);
    }

    @Test
    void itShouldGetNotNullCurrentMoveStatus() {
        MoveStatus expectedMoveStatus = underTest.getCurrentMoveStatus(SESSION_ID);
        assertNotNull(expectedMoveStatus);
    }
}