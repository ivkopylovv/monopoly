package com.game.monopoly.dao;

import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.enums.PlayerRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.game.monopoly.enums.PlayerColour.GREEN;
import static com.game.monopoly.enums.PlayerRole.ADMIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PlayerDAOTest {
    public static final String ACTUAL_NAME = "Masha";
    public static final String SESSION_ID = "111";
    public static final PlayerUniqueName PLAYER_NAME = new PlayerUniqueName(SESSION_ID, ACTUAL_NAME);
    public static final Long ACTUAL_BALANCE = 1000L;
    public static final Integer ACTUAL_POSITION = 0;
    public static final PlayerColour ACTUAL_COLOUR = GREEN;
    public static final PlayerRole ACTUAL_ROLE = ADMIN;

    @Autowired
    private PlayerDAO playerDAO;

    @BeforeEach
    void setUp() {
        Player player = new Player()
                .setUniqueName(PLAYER_NAME)
                .setBalance(ACTUAL_BALANCE)
                .setPosition(ACTUAL_POSITION)
                .setColour(ACTUAL_COLOUR)
                .setRole(ACTUAL_ROLE);
        playerDAO.save(player);
    }

    @AfterEach
    void tearDown() {
        playerDAO.deleteAll();
    }

    @Test
    void itShouldFindPlayerByName() {
        // when
        Player exceptedPlayer = playerDAO.findPlayerByUniqueName(PLAYER_NAME).get();

        // then
        assertEquals(exceptedPlayer.getUniqueName(), PLAYER_NAME);
    }

    @Test
    void itShouldUpdatePlayerBalanceByName() {
        // given
        Long moneyDiff = 200L;

        // when
        playerDAO.updatePlayerBalanceByName(moneyDiff, PLAYER_NAME);
        Long exceptedBalance = playerDAO.findPlayerByUniqueName(PLAYER_NAME).get().getBalance();

        // then
        assertEquals(ACTUAL_BALANCE + moneyDiff, exceptedBalance);
    }

    @Test
    void itShouldUpdatePlayerPositionByName() {
        // given
        Integer position = 5;

        // when
        playerDAO.updatePlayerPositionByName(position, PLAYER_NAME);
        Integer exceptedPosition = playerDAO.findPlayerByUniqueName(PLAYER_NAME).get().getPosition();

        // then
        assertEquals(ACTUAL_POSITION + position, exceptedPosition);
    }
}