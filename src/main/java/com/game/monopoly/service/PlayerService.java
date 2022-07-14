package com.game.monopoly.service;

import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerRole;

public interface PlayerService {
    Player getPlayer(String name);

    void changePlayerBalance(String name, Long moneyDiff);

    void buyCard(String name, Long cardNumber);

    void payForCard(String name, Long cardNumber);

    void savePlayer(String playerName, String colour, PlayerRole role);

    void updatePlayerPosition(int newPosition, String playerName);
}
