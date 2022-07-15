package com.game.monopoly.service;

import com.game.monopoly.dao.NonTypeCardDAO;
import com.game.monopoly.service.impl.NonTypeCardServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class NonTypeCardServiceTest {

    @Mock
    private NonTypeCardDAO nonTypeCardDAO;

    private NonTypeCardService underTest;

    private  AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new NonTypeCardServiceImpl(nonTypeCardDAO);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetNonTypeCards() {
        //when
        underTest.getNonTypeCards();
        //then
        verify(nonTypeCardDAO).findAll();
    }
}