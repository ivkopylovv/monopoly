package com.game.monopoly.dao;

import com.game.monopoly.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PlayerDAOTest {
    public static final String actualName = "Masha";
    public static final Long actualBalance = 1000L;
    public static final Integer actualPosition = 0;

    @Autowired
    private PlayerDAO playerDAO;

    @BeforeEach
    void setUp() {
        Player player = new Player()
                .setName(actualName)
                .setBalance(actualBalance)
                .setPosition(actualPosition);
        playerDAO.save(player);
    }

    @AfterEach
    void tearDown() {
        playerDAO.deleteAll();
    }

    @Test
    void itShouldFindPlayerByName() {
        // when
        Player exceptedPlayer = playerDAO.findPlayerByName(actualName).get();

        // then
        assertEquals(exceptedPlayer.getName(), actualName);
    }

    @Test
    void itShouldUpdatePlayerBalanceByName() {
        // given
        Long moneyDiff = 200L;

        // when
        playerDAO.updatePlayerBalanceByName(moneyDiff, actualName);
        Long exceptedBalance = playerDAO.findPlayerByName(actualName).get().getBalance();

        // then
        assertEquals(actualBalance + moneyDiff, exceptedBalance);
    }

    @Test
    void itShouldUpdatePlayerPositionByName() {
        // given
        Integer position = 5;

        // when
        playerDAO.updatePlayerPositionByName(position, actualName);
        Integer exceptedPosition = playerDAO.findPlayerByName(actualName).get().getPosition();

        // then
        assertEquals(actualPosition + position, exceptedPosition);
    }
}