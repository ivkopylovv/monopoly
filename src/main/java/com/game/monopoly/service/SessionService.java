package com.game.monopoly.service;

import java.util.List;

public interface SessionService {

    void createSession(String sessionId, String playerName);

    void addPlayer(String sessionId, String playerName);

    List<Long> randomSteps(String sessionId, String playerName);
}
