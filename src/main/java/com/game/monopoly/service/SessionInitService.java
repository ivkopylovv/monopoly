package com.game.monopoly.service;

import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.entity.CardState;

import java.util.List;

public interface SessionInitService {
    void saveSession(String sessionId, String playerName, String colour, List<CardState> cardStates);

    PlayingFieldDTO getPlayingField(String sessionId);
}
