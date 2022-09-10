package com.game.monopoly.service;

import com.game.monopoly.dao.*;
import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.dto.response.PayForCardDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.PlayerRole;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.service.impl.CardActionServiceImpl;
import com.game.monopoly.service.impl.PlayerServiceImpl;
import com.game.monopoly.service.impl.SessionCommonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_PLAYER_POSITION;
import static com.game.monopoly.enums.CardType.COMPANY;
import static com.game.monopoly.enums.MoveStatus.MIDDLE;
import static com.game.monopoly.enums.PlayerColour.GREEN;
import static com.game.monopoly.enums.PlayerRole.USER;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
@ComponentScan(basePackages = {"com.game.monopoly.entity"})
@ComponentScan(basePackages = {"com.game.monopoly.helper"})
class CardActionServiceTest {
    public static final String SESSION_ID = "222222";
    public static final String OWNER_NAME = "Ma";
    public static final long PLAYER_ID = 10L;
    public static final String ACTUAL_NAME = "Ma";
    public static final String ACTUAL_COLOUR = "GREEN";
    public static final PlayerRole PLAYER_ROLE = USER;

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private CompanyCardDAO companyCardDAO;
    @Autowired
    private CommonCardDAO commonCardDAO;
    @Autowired
    private CardStateDAO cardStateDAO;
    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private PlayerDAO playerDAO;

    private PlayerService playerService;
    private SessionCommonService sessionCommonService;

    private CardActionService underTest;
    private CardState cardState;

    @BeforeEach
    void setUp() {
        playerService = new PlayerServiceImpl(playerDAO);
        sessionCommonService = new SessionCommonServiceImpl(sessionDAO);
        underTest = new CardActionServiceImpl(sessionDAO, messageDAO, cardStateDAO,sessionCommonService, playerService);
        List<LevelFine> levelFines = new ArrayList<>();
        CommonCard commonCard = new CommonCard(3L, "Image", COMPANY);
        commonCardDAO.save(commonCard);
        CompanyCard companyCard = new CompanyCard(12L, "Hi", "Покупки", 15L,
                10L, 2L, 1, levelFines, commonCard);
        companyCardDAO.save(companyCard);
        //CardState
        cardState = new CardState(4L, 20L, 2, null, companyCard);
        cardStateDAO.save(cardState);

        Player player = new Player()
                .setId(PLAYER_ID)
                .setUniqueName(new PlayerUniqueName(SESSION_ID, ACTUAL_NAME))
                .setPosition(INITIAL_PLAYER_POSITION)
                .setColour(GREEN)
                .setRole(PLAYER_ROLE);
        playerDAO.save(player);

        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(null)
                .setMoveStatus(MIDDLE);
        session.getCardStates().add(cardState);
        session.getPlayers().add(player);
        sessionDAO.save(session);

    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
        cardStateDAO.deleteAll();
        playerDAO.deleteAll();
        companyCardDAO.deleteAll();
        commonCardDAO.deleteAll();
    }

    @Test
    void itShouldCatchResourceNotFoundExceptionForBuyingCard() {
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            CardStatePlayerBalanceDTO cardStatePlayerBalanceDTO = underTest.buyCard(SESSION_ID, OWNER_NAME, 1L);
        });
        Assertions.assertEquals("Card Not Found", thrown.getMessage());
    }

    @Test
    void itShouldCatchResourceNotFoundExceptionForImprovingCard() {
        String notExistedPlayer = "Van";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            CardStatePlayerBalanceDTO cardStatePlayerBalanceDTO = underTest.improveCard(
                    SESSION_ID, notExistedPlayer, 12L);
        });
        Assertions.assertEquals("Player Not Found", thrown.getMessage());
    }
    @Test
    void itShouldCatchResourceNotFoundExceptionForSellingCard() {
        String notExistedSession = "100";
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            CardStatePlayerBalanceDTO cardStatePlayerBalanceDTO = underTest.sellCard(notExistedSession, OWNER_NAME, 12L);
        });
        Assertions.assertEquals("Session Not Found", thrown.getMessage());
    }
    @Test
    void itShouldCatchResourceNotFoundExceptionForPayingForCard() {
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            PayForCardDTO payForCard = underTest.payForCard(SESSION_ID, OWNER_NAME, 12L);
        });
        Assertions.assertEquals("Card Not Found", thrown.getMessage());
    }
