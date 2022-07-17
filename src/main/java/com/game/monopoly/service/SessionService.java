package com.game.monopoly.service;

import com.game.monopoly.dto.response.BuyCardDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;

import java.util.List;

public interface SessionService {
    Session getSession(String sessionId);

    void checkSessionExists(String sessionId);

    void saveSession(String sessionId, String playerName, String colour, List<CardState> cardStates);

    Player addPlayerToSession(String sessionId, String playerName, String colour);

    RollDiceResultDTO rollDices(String sessionId, String playerName);

    void startGame(String sessionId, String nextPlayer);

    BuyCardDTO buyCard(String sessionId, String playerName, Long cardId);

    String getNextPlayer(String sessionId, String nextPlayer);

    void addCommonMessageToChatHistory(String sessionId, String sender, String message);
}
