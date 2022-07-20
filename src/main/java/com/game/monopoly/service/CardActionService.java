package com.game.monopoly.service;

import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.dto.response.PayForCardDTO;

public interface CardActionService {
    PayForCardDTO payForCard(String sessionId, String buyerName, Long cardId);

    CardStatePlayerBalanceDTO buyCard(String sessionId, String playerName, Long cardId);

    CardStatePlayerBalanceDTO improveCard(String sessionId, String playerName, Long cardId);

    CardStatePlayerBalanceDTO sellCard(String sessionId, String playerName, Long cardId);
}
