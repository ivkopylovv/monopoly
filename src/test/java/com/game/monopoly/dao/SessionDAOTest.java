package com.game.monopoly.dao;

import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.SessionState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.game.monopoly.enums.SessionState.IN_PROGRESS;
import static com.game.monopoly.enums.SessionState.NEW;
import static java.lang.Boolean.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SessionDAOTest {

    public static final String ACTUAL_ID = "123";
    public static final SessionState ACTUAL_STATE = NEW;

    @Autowired
    private SessionDAO sessionDAO;

    @BeforeEach
    void setUp() {
        Session session = new Session().setId(ACTUAL_ID).setState(ACTUAL_STATE);
        sessionDAO.save(session);
    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
    }

    @Test
    void itShouldUpdateSessionStateAndCurrentPlayer() {
        // given
        String nextPlayer = "Vanya";
        SessionState newState = IN_PROGRESS;

        // when
        sessionDAO.updateSessionStateAndCurrentPlayer(newState, nextPlayer, ACTUAL_ID);
        SessionState exceptedSessionState = sessionDAO.findById(ACTUAL_ID).get().getState();
        String exceptedNextPlayer = sessionDAO.findById(ACTUAL_ID).get().getCurrentPlayer();

        // then
        assertEquals(nextPlayer, exceptedNextPlayer);
        assertEquals(newState, exceptedSessionState);

    }

    @Test
    void itShouldUpdateCurrentPlayer() {
        // given
        String nextPlayer = "Vanya";

        // when
        sessionDAO.updateCurrentPlayer(nextPlayer, ACTUAL_ID);
        String exceptedNextPlayer = sessionDAO.findById(ACTUAL_ID).get().getCurrentPlayer();

        // then
        assertEquals(nextPlayer, exceptedNextPlayer);

    }

    @Test
    void itShouldCheckSessionExistence() {
        Boolean sessionExists = TRUE;
        Boolean isExists = sessionDAO.existsSessionById(ACTUAL_ID);

        assertEquals(sessionExists, isExists);

    }

}
