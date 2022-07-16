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
    void itShouldUpdateSessionState() {
        // given
        SessionState newState = IN_PROGRESS;

        // when
        sessionDAO.updateSessionState(newState, ACTUAL_ID);
        SessionState exceptedState = sessionDAO.findById(ACTUAL_ID).get().getState();

        // then
        assertEquals(newState, exceptedState);

    }
}
