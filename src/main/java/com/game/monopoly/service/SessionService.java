package com.game.monopoly.service;

import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Player;

public interface SessionService {
    PlayingFieldDTO getPlayingField(String sessionId);

    void createSession(String sessionId, String playerName, String colour);

    Player addPlayer(String sessionId, String playerName, String colour);

    RollDiceResultDTO rollDices(String sessionId, String playerName);
}
