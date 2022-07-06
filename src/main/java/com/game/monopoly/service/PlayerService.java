package com.game.monopoly.service;

public interface PlayerService {
    void changePlayerBalance(String name, Long moneyDiff);

    void buyCard(String name, Long cardNumber);

    void payForCard(String name, Long cardNumber);
}
