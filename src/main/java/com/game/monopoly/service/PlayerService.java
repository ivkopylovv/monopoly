package com.game.monopoly.service;

import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerRole;

public interface PlayerService {
    Player getPlayer(String name);

    void savePlayer(String playerName, String colour, PlayerRole role);

    void updatePlayerPosition(int newPosition, String playerName);

    void updatePlayerBalance(Long moneyDiff, String playerName);
}
