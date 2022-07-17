package com.game.monopoly.service;

import com.game.monopoly.dao.CommonCardDAO;
import com.game.monopoly.service.impl.CommonCardServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CommonCardServiceTest {
    @Mock
    private CommonCardDAO commonCardDAO;
    private CommonCardService underTest;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CommonCardServiceImpl(commonCardDAO);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetChanceCards() {
        //when
        underTest.getAllCards();
        //then
        verify(commonCardDAO).findAll();
    }

}
