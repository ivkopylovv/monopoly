package com.game.monopoly.service;

public interface MessageService {
    void saveCommonMessage(String sessionId, String sender, String message);

    void saveSingleMessage(String sessionId, String sender, String receiver, String message);
}
