package com.game.monopoly.service.impl;

import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.exception.ResourceAlreadyExistsException;
import com.game.monopoly.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static com.game.monopoly.constants.ErrorMessage.PLAYER_ALREADY_EXISTS;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;

    @Override
    public void changePlayerBalance(String name, Long moneyDiff) {
        playerDAO.updatePlayerBalanceByName(moneyDiff, name);
    }

    @Override
    public void buyCard(String name, Long cardNumber) {

    }

    @Override
    public void payForCard(String name, Long cardNumber) {

    }

    @Override
    public Player savePlayer(String playerName, String colour) {
        playerDAO.findPlayerByName(playerName)
                .ifPresent((player -> {
                    throw new ResourceAlreadyExistsException(PLAYER_ALREADY_EXISTS);
                }));
        Player player = new Player()
                .setName(playerName)
                .setPosition(0)
                .setColour(PlayerColour.valueOf(colour.toUpperCase(Locale.ROOT)));
        return playerDAO.save(player);
    }
}
