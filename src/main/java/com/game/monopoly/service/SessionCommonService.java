package com.game.monopoly.service;

import com.game.monopoly.entity.Session;

public interface SessionCommonService {
    Session getSession(String sessionId);

    void checkSessionExists(String sessionId);
}
