package com.game.monopoly.service;

import com.game.monopoly.dao.ChanceCardDAO;
import com.game.monopoly.service.impl.ChanceCardServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ChanceCardServiceTest {

    @Mock
    private ChanceCardDAO chanceCardDAO;
    private ChanceCardService underTest;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ChanceCardServiceImpl(chanceCardDAO);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetChanceCards() {
        //when
        underTest.getChanceCards();
        //then
        verify(chanceCardDAO).findAll();
    }
}
