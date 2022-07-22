package com.game.monopoly.service;

import com.game.monopoly.dao.MessageDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.Session;
import com.game.monopoly.service.impl.ChatServiceImpl;
import com.game.monopoly.service.impl.SessionCommonServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_MOVE_STATUS;
import static org.junit.Assert.assertNotNull;
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
    @Autowired
    private MessageDAO messageDAO;

    private SessionCommonService sessionCommonService;

    private ChatService underTest;

    @BeforeEach
    void setUp() {
        sessionCommonService = new SessionCommonServiceImpl(sessionDAO);
        underTest = new ChatServiceImpl(messageDAO, sessionCommonService);
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
    void itShouldAssertNotNullMessage() {
        String newMessage = "Hello";
        underTest.addCommonMessageToChatHistory(SESSION_ID, SENDER_NAME, newMessage);
        //assertEquals(newMessage, sessionDAO.findById(SESSION_ID).get().getMessages().get(0).getContent());
        assertNotNull(sessionDAO.findById(SESSION_ID).get().getMessages());
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
