package com.game.monopoly.service;

import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerRole;

public interface PlayerService {
    Player getPlayer(String sessionId, String name);

    void savePlayer(String sessionId, String playerName, String colour, PlayerRole role);

    void updatePlayerPosition(int newPosition, String sessionId, String playerName);

    void updatePlayerBalance(Long newBalance, String sessionId, String playerName);
}
