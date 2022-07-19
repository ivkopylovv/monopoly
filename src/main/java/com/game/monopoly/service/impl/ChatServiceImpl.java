package com.game.monopoly.service.impl;

import com.game.monopoly.dao.MessageDAO;
import com.game.monopoly.entity.Message;
import com.game.monopoly.entity.Session;
import com.game.monopoly.helper.MessageHelper;
import com.game.monopoly.service.ChatService;
import com.game.monopoly.service.SessionCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final MessageDAO messageDAO;
    private final SessionCommonService sessionCommonService;

    @Transactional
    @Override
    public void addCommonMessageToChatHistory(String sessionId, String sender, String message) {
        Session session = sessionCommonService.getSession(sessionId);
        Message newMessage = MessageHelper.createSentMessage(message, sender);
        messageDAO.save(newMessage);
        session.getMessages().add(newMessage);
    }
}
