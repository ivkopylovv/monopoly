package com.game.monopoly.controller;

import com.game.monopoly.dto.request.PerformActionWithCardDTO;
import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.dto.response.PayForCardDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.service.CardActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.game.monopoly.constants.ResultMessage.*;

@Controller
@RequiredArgsConstructor
public class CardActionController {
    private final CardActionService cardActionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/sessions/buy-card")
    public void buyCard(PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.buyCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), BUY_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/improve-card")
    public void improveCard(PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.improveCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), IMPROVE_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/sell-card")
    public void sellCard(PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.sellCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        CardStateDTO cardState = result.getCardState().get(dto.getCardId());
        ResultMessageDTO resultMessage;

        if (cardState.getOwnerName() == null) {
            resultMessage = new ResultMessageDTO(dto.getPlayerName(), SELL_CARD);
        } else {
            resultMessage = new ResultMessageDTO(dto.getPlayerName(), LOWER_CARD_LEVEL);
        }

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/pay-for-card")
    public void payForCard(PerformActionWithCardDTO dto) {
        PayForCardDTO result = cardActionService.payForCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), PAY_FOR_CARD);

        simpMessagingTemplate.convertAndSend("/topic/pay-for-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }
}
