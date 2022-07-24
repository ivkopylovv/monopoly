package com.game.monopoly.service;

import com.game.monopoly.dao.ChanceCardDAO;
import com.game.monopoly.entity.ChanceCard;
import com.game.monopoly.service.impl.ChanceCardServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static com.game.monopoly.enums.ChanceCardType.CHANGE_BALANCE;
import static com.game.monopoly.enums.ChanceCardType.CHANGE_POSITION;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = {"com.game.monopoly.service"})
@ComponentScan(basePackages = {"com.game.monopoly.dao"})
class ChanceCardServiceTest {

    @Autowired
    private ChanceCardDAO chanceCardDAO;

    private ChanceCardService underTest;

    @BeforeEach
    void setUp() {
        ChanceCard chanceCard = new ChanceCard()
                .setId(1L)
                .setDescription("Get 100k")
                .setType(CHANGE_BALANCE);
        chanceCardDAO.save(chanceCard);
        underTest = new ChanceCardServiceImpl(chanceCardDAO);
    }

    @AfterEach
    void tearDown() {
        chanceCardDAO.deleteAll();
    }

    @Test
    void canGetRandomChanceCard() {
        assertNotNull(underTest.getRandomChanceCard());
    }

    @Test
    void itShouldGetNotNullChanceCardCount() {
        assertNotNull(underTest.getChanceCardCount());
    }

    @Test
    void itShouldGetChanceCardCount() {
        Long amountChanceCard = 1L;
        assertEquals(amountChanceCard, underTest.getChanceCardCount());
    }

    @Test
    void itShouldGetThreeChanceCard() {
        Long amountChanceCard = 3L;
        ChanceCard chanceCard2 = new ChanceCard()
                .setId(2L)
                .setDescription("Get 200k")
                .setType(CHANGE_BALANCE);
        chanceCardDAO.save(chanceCard2);
        ChanceCard chanceCard3 = new ChanceCard()
                .setId(3L)
                .setDescription("Go forward on 10 steps")
                .setType(CHANGE_POSITION);
        chanceCardDAO.save(chanceCard3);
        assertEquals(amountChanceCard, underTest.getChanceCardCount());
    }

}