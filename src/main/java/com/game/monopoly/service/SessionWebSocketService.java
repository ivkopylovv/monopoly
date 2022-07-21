package com.game.monopoly.service;

import com.game.monopoly.dto.response.ChangeBalanceCardStateDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.dto.response.SurrenderPlayerDTO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.MoveStatus;

import java.util.List;

public interface SessionWebSocketService {
    Player addPlayerToSession(String sessionId, String playerName, String colour);

    RollDiceResultDTO rollDices(String sessionId, String playerName);

    void startGame(String sessionId, String nextPlayer);

    String getNextPlayer(String sessionId, String nextPlayer);

    MoveStatus getCurrentMoveStatus(String sessionId);

    SurrenderPlayerDTO getSurrenderPlayer(String sessionId, String playerName);

    ChangeBalanceCardStateDTO acceptOffer(String sessionId, String playerName, String ownerName, Long money, List<Long> cardIds);
}
