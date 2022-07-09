package com.game.monopoly.dao;

import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerColour;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.game.monopoly.enums.PlayerColour.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PlayerDAOTest {
    public static final String ACTUAL_NAME = "Masha";
    public static final Long ACTUAL_BALANCE = 1000L;
    public static final Integer ACTUAL_POSITION = 0;
    public static final PlayerColour ACTUAL_COLOUR = GREEN;

    @Autowired
    private PlayerDAO playerDAO;

    @BeforeEach
    void setUp() {
        Player player = new Player()
                .setName(ACTUAL_NAME)
                .setBalance(ACTUAL_BALANCE)
                .setPosition(ACTUAL_POSITION)
                .setColour(ACTUAL_COLOUR);
        playerDAO.save(player);
    }

    @AfterEach
    void tearDown() {
        playerDAO.deleteAll();
    }

    @Test
    void itShouldFindPlayerByName() {
        // when
        Player exceptedPlayer = playerDAO.findPlayerByName(ACTUAL_NAME).get();

        // then
        assertEquals(exceptedPlayer.getName(), ACTUAL_NAME);
    }

    @Test
    void itShouldUpdatePlayerBalanceByName() {
        // given
        Long moneyDiff = 200L;

        // when
        playerDAO.updatePlayerBalanceByName(moneyDiff, ACTUAL_NAME);
        Long exceptedBalance = playerDAO.findPlayerByName(ACTUAL_NAME).get().getBalance();

        // then
        assertEquals(ACTUAL_BALANCE + moneyDiff, exceptedBalance);
    }

    @Test
    void itShouldUpdatePlayerPositionByName() {
        // given
        Integer position = 5;

        // when
        playerDAO.updatePlayerPositionByName(position, ACTUAL_NAME);
        Integer exceptedPosition = playerDAO.findPlayerByName(ACTUAL_NAME).get().getPosition();

        // then
        assertEquals(ACTUAL_POSITION + position, exceptedPosition);
    }
}