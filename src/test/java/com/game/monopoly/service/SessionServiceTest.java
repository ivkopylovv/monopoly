package com.game.monopoly.service;

import com.game.monopoly.dao.*;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.enums.PlayerRole;
import com.game.monopoly.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static com.game.monopoly.enums.PlayerColour.GREEN;
import static com.game.monopoly.enums.PlayerRole.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@DataJpaTest
class SessionServiceTest {

    public static final String SESSION_ID = "111";
    public static final String ACTUAL_NAME = "Masha";
    public static final PlayerUniqueName PLAYER_ACTUAL_NAME = new PlayerUniqueName(SESSION_ID, ACTUAL_NAME);
    public static final PlayerColour ACTUAL_COLOUR = GREEN;
    public static final Integer ACTUAL_POSITION = 0;
    public static final PlayerRole PLAYER_ROLE = USER;

    public static final Long ACTUAL_BALANCE = 1000L;

    @Mock
    private SessionDAO sessionDAO;
    @Mock
    private PlayerDAO playerDAO;

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private CompanyCardDAO companyCardDAO;

    @Mock
    private CardStateDAO cardStateDAO;


    @Mock
    private PlayerService playerService;

    @Mock
    private CardStateService cardStateService;

    @Mock
    private CompanyCardService companyCardService;

    private AutoCloseable autoCloseable;


    @Mock
    private SessionService underTest;

    @Mock
    private Session session;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SessionServiceImpl(sessionDAO, messageDAO, cardStateDAO, playerService);
        //companyCardService = new CompanyCardServiceImpl(companyCardDAO);
        //cardStateService = new CardStateServiceImpl(cardStateDAO);
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        cardStateService.saveCardStates(cardStates);
        underTest.saveSession(SESSION_ID, ACTUAL_NAME, "GREEN", cardStates);
        sessionDAO.save(new Session().setId(SESSION_ID));
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetSession() {
        Optional<Session> expectedSession = sessionDAO.findById(SESSION_ID);
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        Session session1 = new Session();
        //Session expectedSession = new Session(SESSION_ID, PLAYER_ACTUAL_NAME, "GREEN", cardStates);
        //assertThat(expectedSession).isEqualTo();


    }

    @Test
    void canCreateSession() {
        //underTest.createSession(SESSION_ID, PLAYER_ACTUAL_NAME, String.valueOf(ACTUAL_COLOUR));
        //underTest.createSession(SESSION_ID);
        //verify(sessionDAO).save(new Session());

    }

    @Test
    void canAddPlayer() {
        //Session session1 = getSession(SESSION_ID);
        //session = sessionDAO.save(new Session().setId(SESSION_ID).setState(SessionState.NEW));
        //sessionDAO.findById(SESSION_ID);
        //sessionDAO.save(new Session().setId(SESSION_ID));
        session = underTest.getSession(SESSION_ID);
        //session = underTest.
        Player player = new Player()
                .setUniqueName(PLAYER_ACTUAL_NAME)
                .setPosition(ACTUAL_POSITION)
                .setRole(PLAYER_ROLE)
                .setColour(PlayerColour.valueOf(String.valueOf(ACTUAL_COLOUR)))
                .setBalance(ACTUAL_BALANCE);
        underTest.addPlayerToSession(SESSION_ID, ACTUAL_NAME, String.valueOf(ACTUAL_COLOUR));
        session.getPlayers().add(player);
        ArgumentCaptor<Player> playerArgumentCaptor =
                ArgumentCaptor.forClass(Player.class);
        verify(playerDAO)
                .save(playerArgumentCaptor.capture());
        Player capturedPlayer = playerArgumentCaptor.getValue();
        assertThat(capturedPlayer).isEqualTo(player);
    }
}