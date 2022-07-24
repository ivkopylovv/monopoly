package com.game.monopoly.service;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.dao.ChanceCardDAO;
import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.impl.CardStateServiceImpl;
import com.game.monopoly.service.impl.ChanceCardServiceImpl;
import com.game.monopoly.service.impl.CompanyCardServiceImpl;
import com.game.monopoly.service.impl.SessionCommonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
class CardStateServiceTest {
    @Autowired
    private CardStateDAO cardStateDAO;
    @Autowired
    private CompanyCardDAO companyCardDAO;
    @Autowired
    private SessionDAO sessionDAO;

    private CompanyCardService companyCardService;
    private SessionCommonService sessionCommonService;

    private CardStateService underTest;

    @BeforeEach
    void setUp() {
        sessionCommonService = new SessionCommonServiceImpl(sessionDAO);
        companyCardService = new CompanyCardServiceImpl(sessionCommonService, companyCardDAO);
        underTest = new CardStateServiceImpl(cardStateDAO);
    }

    @AfterEach
    void tearDown() {
        cardStateDAO.deleteAll();
    }

    @Test
    void canGetNewCardStates() {
        //when
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        //then
        assertEquals(underTest.getNewCardStates(companyCards), companyCards);
    }

    @Test
    void canFindByCardIds() {
        List<Long> cardIds = new ArrayList<>();
        cardIds.add(1L);
        cardIds.add(2L);
        cardIds.add(3L);
        assertNotNull(underTest.findByCardIds(cardIds));
    }

    @Test
    void itShouldGetEmptyCardIds() {
        List<Long> cardIds = new ArrayList<>();
        assertTrue(underTest.findByCardIds(cardIds).isEmpty());
    }


}