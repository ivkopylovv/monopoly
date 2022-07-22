package com.game.monopoly.service;

import com.game.monopoly.dto.response.PlayingFieldStateDTO;
import com.game.monopoly.dto.response.PlayingFieldStaticDTO;
import com.game.monopoly.entity.CardState;

import java.util.List;

public interface SessionHttpService {
    void saveSession(String sessionId, String playerName, String colour, List<CardState> cardStates);

    PlayingFieldStateDTO getStatePlayingField(String sessionId);

    PlayingFieldStaticDTO getStaticPlayingField();

    void finishSession(String sessionId);
}
