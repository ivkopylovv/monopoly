package com.game.monopoly.service;

import com.game.monopoly.dto.response.PlayerStatusDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.MoveStatus;

public interface SessionProcessService {
    Player addPlayerToSession(String sessionId, String playerName, String colour);

    RollDiceResultDTO rollDices(String sessionId, String playerName);

    void startGame(String sessionId, String nextPlayer);

    String getNextPlayer(String sessionId, String nextPlayer);

    MoveStatus getCurrentMoveStatus(String sessionId);

    PlayerStatusDTO getSurrenderPlayer(String sessionId, String playerName);
}
