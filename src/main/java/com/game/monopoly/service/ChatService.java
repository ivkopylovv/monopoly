package com.game.monopoly.service;

public interface ChatService {
    void addCommonMessageToChatHistory(String sessionId, String sender, String message);
}
