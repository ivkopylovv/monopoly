package com.game.monopoly.service;

import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;

public interface SessionService {
    PlayingFieldDTO getPlayingField(String sessionId);

    void createSession(String sessionId);

    void addPlayer(String sessionId, String playerName, String colour);

    RollDiceResultDTO rollDices(String sessionId, String playerName);
}
