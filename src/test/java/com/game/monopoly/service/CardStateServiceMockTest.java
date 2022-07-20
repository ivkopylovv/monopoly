package com.game.monopoly.service;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.impl.CardStateServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class CardStateServiceMockTest {
    @Mock
    private CardStateDAO cardStateDAO;
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
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canSaveCardStates() {
        //when
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        underTest.saveCardStates(cardStates);
        //then
        verify(cardStateDAO).saveAll(cardStates);
    }

    @Test
    void canGetNewCardStates() {
        //when
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        //then
        assertEquals(underTest.getNewCardStates(companyCards), companyCards);
    }

}
