package com.game.monopoly.service;

import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.Session;
import com.game.monopoly.exception.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_MOVE_STATUS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
class SessionCommonServiceTest {
    public static final String SESSION_ID = "123";
    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SessionCommonService underTest;

    @BeforeEach
    void setUp() {
        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS);
        sessionDAO.save(session);
    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
    }

    @Test
    void canGetSession() {
        Session expectedSession = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS);
        assertEquals(expectedSession, underTest.getSession(SESSION_ID));
    }

    @Test
    void itShouldCatchResourceNotFoundException() {
        String newSessionID = "111";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            underTest.getSession(newSessionID);
        });
        Assertions.assertEquals("Session Not Found", thrown.getMessage());
    }


}