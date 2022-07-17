package com.game.monopoly.service;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.impl.CardStateServiceImpl;
import com.game.monopoly.service.impl.CompanyCardServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class CardStateServiceTest {
    @Mock
    private CardStateDAO cardStateDAO;
    @Mock
    private CompanyCardDAO companyCardDAO;
    @Mock
    private CardStateService cardStateService;
    @Mock
    private CompanyCardService companyCardService;

    private CardStateService underTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CardStateServiceImpl(cardStateDAO);
        companyCardService = new CompanyCardServiceImpl(companyCardDAO);
        cardStateService = new CardStateServiceImpl(cardStateDAO);
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetChanceCards() {
        //when
        companyCardService = new CompanyCardServiceImpl(companyCardDAO);
        cardStateService = new CardStateServiceImpl(cardStateDAO);
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        underTest.saveCardStates(cardStates);
        //then
        verify(cardStateDAO).saveAll(cardStates);
    }

}
