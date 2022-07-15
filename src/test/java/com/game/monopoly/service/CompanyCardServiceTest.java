package com.game.monopoly.service;

import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.service.impl.CompanyCardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CompanyCardServiceTest {
    @Mock
    private CompanyCardDAO companyCardDAO;
    private CompanyCardService underTest;

    private  AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CompanyCardServiceImpl(companyCardDAO);

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
