package com.game.monopoly.controller;

import com.game.monopoly.dto.request.PerformActionWithCardDTO;
import com.game.monopoly.dto.response.BuyCardDTO;
import com.game.monopoly.dto.response.PayForCardDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.service.CardActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.game.monopoly.constants.ResultMessage.BUY_CARD;
import static com.game.monopoly.constants.ResultMessage.PAY_FOR_CARD;

@Controller
@RequiredArgsConstructor
public class CardActionController {
    private final CardActionService cardActionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping(value = "/sessions/buy-card")
    public ResponseEntity<BuyCardDTO> buyCard(@RequestBody PerformActionWithCardDTO dto) {
        BuyCardDTO result = cardActionService.buyCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), BUY_CARD);

        simpMessagingTemplate.convertAndSend("/topic/buy-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(result);
    }

    @MessageMapping(value = "/sessions/pay-for-card")
    public ResponseEntity<PayForCardDTO> payForCard(PerformActionWithCardDTO dto) {
        PayForCardDTO result = cardActionService.payForCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), PAY_FOR_CARD);

        simpMessagingTemplate.convertAndSend("/topic/pay-for-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(result);
    }
}
