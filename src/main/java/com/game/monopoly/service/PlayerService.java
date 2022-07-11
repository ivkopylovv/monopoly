package com.game.monopoly.service;

import com.game.monopoly.entity.Player;

public interface PlayerService {
    void changePlayerBalance(String name, Long moneyDiff);

    void buyCard(String name, Long cardNumber);

    void payForCard(String name, Long cardNumber);

    Player savePlayer(String playerName, String colour);
}
