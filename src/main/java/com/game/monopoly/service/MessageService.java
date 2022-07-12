package com.game.monopoly.service;

public interface MessageService {

    public void saveCommonMessage(String sessionId, String sender, String message);
    public void saveSingleMessage(String sessionId, String sender, String receiver, String message);

}
