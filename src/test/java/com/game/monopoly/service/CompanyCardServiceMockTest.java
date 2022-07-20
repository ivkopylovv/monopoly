package com.game.monopoly.service;

import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.impl.CompanyCardServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.mockito.Mockito.verify;

public class CompanyCardServiceMockTest {

    public static final String SESSION_ID = "123";
    public static final String ACTUAL_NAME = "Masha";
    @Mock
    private CompanyCardDAO companyCardDAO;
    @Mock
    private SessionCommonService sessionCommonService;

    @Mock
    private SessionInitService sessionInitService;
    @Mock
    private CardStateService cardStateService;
    @Mock
    private CompanyCardService companyCardService;
    private CompanyCardService underTest;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CompanyCardServiceImpl(sessionCommonService, companyCardDAO);

        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        sessionInitService.saveSession(SESSION_ID, ACTUAL_NAME, "GREEN", cardStates);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetCompanyCards() {
        //when
        underTest.getCompanyCards();
        //then
        verify(companyCardDAO).findAll();
    }

}
