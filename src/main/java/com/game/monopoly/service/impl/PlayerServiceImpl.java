package com.game.monopoly.service.impl;

import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.exception.ResourceAlreadyExistsException;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.helper.PlayerPositionHelper;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.mapper.RoleDicesMapper;
import com.game.monopoly.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

import static com.game.monopoly.constants.ErrorMessage.PLAYER_ALREADY_EXISTS;
import static com.game.monopoly.constants.ErrorMessage.PLAYER_NOT_FOUND;
import static com.game.monopoly.constants.PlayingFieldParam.MAX_BORDER;
import static com.game.monopoly.constants.PlayingFieldParam.MIN_BORDER;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;

    @Override
    public Player getPlayer(String name) {
        return playerDAO.findPlayerByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));
    }

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

    @Override
    public RollDiceResultDTO rollDices(Player player) {
        int firstRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int secondRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int newPosition = PlayerPositionHelper.getNewPosition(player.getPosition(), firstRoll, secondRoll);

        playerDAO.updatePlayerPositionByName(newPosition, player.getName());

        return RoleDicesMapper.rollResultTODTO(List.of(firstRoll, secondRoll), player.getName(), newPosition);
    }
}
