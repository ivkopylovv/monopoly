package com.game.monopoly.service;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.dao.CommonCardDAO;
import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.PlayingFieldStateDTO;
import com.game.monopoly.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_MOVE_STATUS;
import static com.game.monopoly.enums.CardType.COMPANY;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
class SessionHttpServiceTest {
    public static final String SESSION_ID = "123";
    public static final String ACTUAL_NAME = "Masha";
    public static final String ACTUAL_COLOUR = "GREEN";
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private CommonCardDAO commonCardDAO;
    @Autowired
    private CompanyCardDAO companyCardDAO;
    @Autowired
    private CardStateDAO cardStateDAO;

    @Autowired
    private SessionHttpService underTest;

    @BeforeEach
    void setUp() {
        List<LevelFine> levelFines = new ArrayList<>();
        CommonCard commonCard = new CommonCard(3L, "Image", COMPANY);
        commonCardDAO.save(commonCard);
        CompanyCard companyCard = new CompanyCard(12L, "Hi", "Покупки", 15L,
                10L, 2L, 1, levelFines, commonCard);
        companyCardDAO.save(companyCard);
        CardState cardState = new CardState(4L, 20L, 2, "Masha", companyCard);
        cardStateDAO.save(cardState);
        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS);
        session.getCardStates().add(cardState);
        sessionDAO.save(session);
    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
        cardStateDAO.deleteAll();
        commonCardDAO.deleteAll();
    }

    @Test
    void canSaveSession() {
        String newSessionId = "222";
        Session expectedSession = new Session()
                .setId(newSessionId)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS);
        underTest.saveSession(newSessionId, ACTUAL_NAME, ACTUAL_COLOUR, null);
        assertEquals(expectedSession, sessionDAO.findById(newSessionId).get());

    }

    @Test
    void itShouldGetNotNullPlayingField() {
        PlayingFieldStateDTO playingFieldStateDTO = underTest.getStatePlayingField(SESSION_ID);
        assertNotNull(playingFieldStateDTO);
    }

    @Test
    void itShouldGetNotEmptyCardStatesArray() {
        PlayingFieldStateDTO playingFieldStateDTO = underTest.getStatePlayingField(SESSION_ID);
        assertFalse(playingFieldStateDTO.getCardStates().isEmpty());
    }
}