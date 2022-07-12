package com.game.monopoly.service;

import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;

import java.util.List;

public interface SessionService {
    Session getSession(String sessionId);

    void saveSession(String sessionId, Player player, List<CardState> cardStates);

    void addPlayerToSession(String sessionId, Player player);
}
