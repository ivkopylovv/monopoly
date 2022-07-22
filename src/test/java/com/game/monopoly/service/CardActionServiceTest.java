package com.game.monopoly.service;

import com.game.monopoly.dao.*;
import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.helper.FindHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import static com.game.monopoly.enums.MoveStatus.MIDDLE;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
@ComponentScan(basePackages = {"com.game.monopoly.entity"})
class CardActionServiceTest {
    public static final String SESSION_ID = "123";
    public static final String OWNER_NAME = "Masha";

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private CompanyCardDAO companyCardDAO;
    @Autowired
    private CommonCardDAO commonCardDAO;
    @Autowired
    private CardStateDAO cardStateDAO;

    @Autowired
    private CardActionService underTest;

    @BeforeEach
    void setUp() {
        List<LevelFine> levelFines = new ArrayList<>();
        CommonCard commonCard = new CommonCard(3L, "Image", COMPANY);
        commonCardDAO.save(commonCard);
        CompanyCard companyCard = new CompanyCard(12L, "Hi", "Покупки", 15L,
                10L, 2L, 1, levelFines, commonCard);
        companyCardDAO.save(companyCard);
        CardState cardState = new CardState(4L, 20L, 2, null, companyCard);
        cardStateDAO.save(cardState);
        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(null)
                .setMoveStatus(MIDDLE);
        session.getCardStates().add(cardState);
        sessionDAO.save(session);
        session.getCardStates().add(cardState);

    }

    @AfterEach
    void tearDown() {
        sessionDAO.deleteAll();
        cardStateDAO.deleteAll();
        companyCardDAO.deleteAll();
        commonCardDAO.deleteAll();
    }

    @Test
    void payForCard() {
    }

    @Test
    void itShouldCatchResourceNotFoundException() {
        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            CardStatePlayerBalanceDTO cardStatePlayerBalanceDTO = underTest.buyCard(SESSION_ID, OWNER_NAME, 12L);
        });
        Assertions.assertEquals("Card Not Found", thrown.getMessage());
    }

//    @Test
//    void canBuyCard() {
//        List<LevelFine> levelFines = new ArrayList<>();
//        CommonCard commonCard = new CommonCard(3L, "Image", COMPANY);
//        commonCardDAO.save(commonCard);
//        CompanyCard companyCard = new CompanyCard(12L, "Hi", "Покупки", 15L,
//                10L, 2L, 1, levelFines, commonCard);
//        companyCardDAO.save(companyCard);
//        CardState cardState = new CardState(4L, 20L, 2, null, companyCard);
//        cardStateDAO.save(cardState);
//        Session session = new Session()
//                .setId(SESSION_ID)
//                .setCurrentPlayer(null)
//                .setMoveStatus(MIDDLE);
//        session.getCardStates().add(cardState);
//        sessionDAO.save(session);
//        //CardState cardState1 = FindHelper.findCardStateByCardId(session.getCardStates(), 12L);
//        CardStatePlayerBalanceDTO cardStatePlayerBalanceDTO = underTest.buyCard(SESSION_ID, OWNER_NAME, 12L);
//        ///CardStatePlayerBalanceDTO cardStatePlayerBalanceDTO = underTest.buyCard(SESSION_ID, OWNER_NAME, 12L);
//        assertNotNull(cardStatePlayerBalanceDTO);
//        //assertNotNull(cardState1);
//        //assertEquals(12L, cardState1.getCard().getId());
//    }

    @Test
    void improveCard() {
    }

    @Test
    void sellCard() {
    }
}