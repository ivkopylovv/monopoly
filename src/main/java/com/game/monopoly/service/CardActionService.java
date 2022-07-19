package com.game.monopoly.service;

import com.game.monopoly.dto.response.BuyCardDTO;
import com.game.monopoly.dto.response.PayForCardDTO;

public interface CardActionService {
    PayForCardDTO payForCard(String sessionId, String buyerName, Long cardId);

    BuyCardDTO buyCard(String sessionId, String playerName, Long cardId);
}
