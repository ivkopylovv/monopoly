package com.game.monopoly.controller;

import com.game.monopoly.dto.request.PerformActionWithCardDTO;
import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.dto.response.PayForCardDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.service.CardActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.game.monopoly.constants.ResultMessage.*;

@RestController
@RequiredArgsConstructor
public class CardActionController {
    private final CardActionService cardActionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping(value = "/sessions/buy-card")
    public void buyCard(@RequestBody PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.buyCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), BUY_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @PostMapping(value = "/sessions/improve-card")
    public void improveCard(@RequestBody PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.improveCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), IMPROVE_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @PostMapping(value = "/sessions/sell-card")
    public void sellCard(@RequestBody PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.sellCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), SELL_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @PostMapping(value = "/sessions/pay-for-card")
    public void payForCard(@RequestBody PerformActionWithCardDTO dto) {
        PayForCardDTO result = cardActionService.payForCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), PAY_FOR_CARD);

        simpMessagingTemplate.convertAndSend("/topic/pay-for-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }
}
