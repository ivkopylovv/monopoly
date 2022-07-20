package com.game.monopoly.dao;

import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.enums.SessionState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.game.monopoly.enums.MoveStatus.*;
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
    void itShouldUpdateSessionStateAndCurrentPlayerAndMoveStatus() {
        // given
        String nextPlayer = "Vanya";
        SessionState newState = IN_PROGRESS;
        MoveStatus newStatus = MIDDLE;

        // when
        sessionDAO.updateSessionStateAndCurrentPlayerAndMoveStatus(newState, nextPlayer, newStatus, ACTUAL_ID);
        SessionState expectedSessionState = sessionDAO.findById(ACTUAL_ID).get().getState();
        String expectedNextPlayer = sessionDAO.findById(ACTUAL_ID).get().getCurrentPlayer();
        MoveStatus expectedStatus = sessionDAO.findById(ACTUAL_ID).get().getMoveStatus();

        // then
        assertEquals(nextPlayer, expectedNextPlayer);
        assertEquals(newState, expectedSessionState);
        assertEquals(newStatus, expectedStatus);

    }

    @Test
    void itShouldUpdateCurrentPlayerAndMoveStatus() {
        // given
        String nextPlayer = "Vanya";
        MoveStatus newStatus = MIDDLE;

        // when
        sessionDAO.updateCurrentPlayerAndMoveStatus(nextPlayer, newStatus, ACTUAL_ID);
        String expectedNextPlayer = sessionDAO.findById(ACTUAL_ID).get().getCurrentPlayer();
        MoveStatus expectedStatus = sessionDAO.findById(ACTUAL_ID).get().getMoveStatus();

        // then
        assertEquals(nextPlayer, expectedNextPlayer);
        assertEquals(newStatus, expectedStatus);

    }

    @Test
    void itShouldUpdateMoveStatus() {
        // given
        MoveStatus newStatus = MIDDLE;

        // when
        sessionDAO.updateMoveStatus(newStatus, ACTUAL_ID);
        MoveStatus expectedStatus = sessionDAO.findById(ACTUAL_ID).get().getMoveStatus();

        // then
        assertEquals(newStatus, expectedStatus);

    }

    @Test
    void itShouldCheckSessionExistence() {
        Boolean sessionExists = TRUE;
        Boolean isExists = sessionDAO.existsSessionById(ACTUAL_ID);

        assertEquals(sessionExists, isExists);

    }

}
