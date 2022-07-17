package com.game.monopoly.service.impl;

import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.enums.PlayerRole;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static com.game.monopoly.constants.ErrorMessage.PLAYER_NOT_FOUND;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_PLAYER_POSITION;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;

    @Transactional(readOnly = true)
    @Override
    public Player getPlayer(String sessionId, String name) {
        return playerDAO.findPlayerByUniqueName(new PlayerUniqueName(sessionId, name))
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));
    }

    @Transactional
    @Override
    public void savePlayer(String sessionId, String playerName, String colour, PlayerRole role) {
        Player player = new Player()
                .setUniqueName(new PlayerUniqueName(sessionId, playerName))
                .setPosition(INITIAL_PLAYER_POSITION)
                .setColour(PlayerColour.valueOf(colour.toUpperCase(Locale.ROOT)))
                .setRole(role);

        playerDAO.save(player);
    }

    @Transactional
    @Override
    public void updatePlayerPosition(int newPosition, String sessionId, String playerName) {
        playerDAO.updatePlayerPositionByName(newPosition, new PlayerUniqueName(sessionId, playerName));
    }

    @Transactional
    @Override
    public void updatePlayerBalance(Long moneyDiff, String sessionId, String playerName) {
        playerDAO.updatePlayerBalanceByName(moneyDiff, new PlayerUniqueName(sessionId, playerName));
    }

}
