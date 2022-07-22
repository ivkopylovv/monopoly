package com.game.monopoly.service;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.dao.CommonCardDAO;
import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.CardDetailDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.service.impl.CompanyCardServiceImpl;
import com.game.monopoly.service.impl.SessionCommonServiceImpl;
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
public class CompanyCardServiceTest {
    public static final String SESSION_ID = "123";
    public static final String OWNER_NAME = "Masha";

    @Autowired
    private CompanyCardDAO companyCardDAO;
    @Autowired
    private CommonCardDAO commonCardDAO;
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private CardStateDAO cardStateDAO;

    private SessionCommonService sessionCommonService;

    private CompanyCardService underTest;

    @BeforeEach
    void setUp() {
        sessionCommonService = new SessionCommonServiceImpl(sessionDAO);
        underTest = new CompanyCardServiceImpl(sessionCommonService, companyCardDAO);
        List<LevelFine> levelFines = new ArrayList<>();
        CommonCard commonCard = new CommonCard(3L, "Image", COMPANY);
        commonCardDAO.save(commonCard);
        CompanyCard companyCard = new CompanyCard(12L, "Hi", "Покупки", 15L,
                10L, 2L, 1, levelFines, commonCard);
        companyCardDAO.save(companyCard);
        CardState cardState = new CardState(4L, 20L, 2, OWNER_NAME, companyCard);
        cardStateDAO.save(cardState);
        Session session = new Session()
                .setId(SESSION_ID)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS);
        session.getCardStates().add(cardState);
        sessionDAO.save(session);
    }

    @AfterEach
    void tearDown() throws Exception {
        sessionDAO.deleteAll();
        cardStateDAO.deleteAll();
        companyCardDAO.deleteAll();
        commonCardDAO.deleteAll();
    }

    @Test
    void itShouldGetNotNullCardDetailDTO() {
        CardDetailDTO cardDetailDTO = underTest.getDetailedCardInfo(SESSION_ID, 12L);
        assertNotNull(cardDetailDTO);
    }

    @Test
    void itShouldGetEmptyLevelFinesArray() {
        CardDetailDTO cardDetailDTO = underTest.getDetailedCardInfo(SESSION_ID, 12L);
        assertTrue(cardDetailDTO.getFines().isEmpty());
    }

    @Test
    void itShouldCompareOwnerName() {
        CardDetailDTO cardDetailDTO = underTest.getDetailedCardInfo(SESSION_ID, 12L);
        assertEquals(OWNER_NAME, cardDetailDTO.getOwnerName());
    }

    @Test
    void itShouldCompareNotEqualOwnerName() {
        String ownerName = "Jack";
        CardDetailDTO cardDetailDTO = underTest.getDetailedCardInfo(SESSION_ID, 12L);
        assertNotEquals(ownerName, cardDetailDTO.getOwnerName());
    }

    @Test
    void itShouldCompareTitle() {
        String expectedTitle = "Hi";
        CardDetailDTO cardDetailDTO = underTest.getDetailedCardInfo(SESSION_ID, 12L);
        assertEquals(expectedTitle, cardDetailDTO.getTitle());
    }

    @Test
    void itShouldCompareNotEqualShpere() {
        String notExpectedTitle = "Одежда";
        CardDetailDTO cardDetailDTO = underTest.getDetailedCardInfo(SESSION_ID, 12L);
        assertNotEquals(notExpectedTitle, cardDetailDTO.getSphere());
    }

}
