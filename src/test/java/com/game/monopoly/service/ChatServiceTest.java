package com.game.monopoly.service;

import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.Session;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
public class ChatServiceTest {
    public static final String SESSION_ID = "111";
    public static final String SENDER_NAME = "Masha";
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired(required = false)
    private ChatService underTest;

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
    void itShouldAddCommonMessageToChatHistory() {
        String newMessage = "Hello, world!";
        underTest.addCommonMessageToChatHistory(SESSION_ID, SENDER_NAME, newMessage);
        assertEquals(newMessage, sessionDAO.findById(SESSION_ID).get().getMessages().get(0).getContent());
    }

    @Test
    void itShouldCatchNullPointerExceptionWhenMessageIsNull() {
        String newMessage = null;
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            underTest.addCommonMessageToChatHistory(SESSION_ID, SENDER_NAME, newMessage);
            sessionDAO.findById(SESSION_ID).get().getMessages().get(0).getContent().isEmpty();
        });
        assertTrue(thrown.getMessage().contains("null"));
    }
}
