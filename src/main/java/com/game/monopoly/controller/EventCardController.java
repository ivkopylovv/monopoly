package com.game.monopoly.controller;

import com.game.monopoly.dto.request.JackpotEventDTO;
import com.game.monopoly.dto.response.PlayerBalanceDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.helper.CardEventHelper;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.mapper.MessageContentMapper;
import com.game.monopoly.service.ChatService;
import com.game.monopoly.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.game.monopoly.constants.EventParam.JACKPOT_BET;

@Controller
@RequiredArgsConstructor
public class EventCardController {
    private final PlayerService playerService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/cards/jackpot")
    public void handleJackpotEvent(JackpotEventDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();
        List<Integer> digits = dto.getDigits();
        String message;

        Long balance = playerService.getPlayer(sessionId, playerName).getBalance();
        int digit = RandomHelper.getRandomDiceValue();

        if (CardEventHelper.isArrayContainNumber(digits, digit)) {
            Long benefit = CardEventHelper.getJackpotBenefit(digits.size());
            balance += benefit;
            message = MessageContentMapper
                    .jackpotEventWinToMessageContent(digits, digit, benefit);
        } else {
            balance -= JACKPOT_BET;
            message = MessageContentMapper
                    .jackpotEventLoseToMessageContent(digits, digit, JACKPOT_BET);
        }

        PlayerBalanceDTO result = new PlayerBalanceDTO(playerName, balance);
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, message);

        playerService.updatePlayerBalance(balance, sessionId, playerName);
        chatService.addCommonMessageToChatHistory(sessionId, playerName, message);

        simpMessagingTemplate.convertAndSend("/topic/change-balance/" + sessionId, result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }


}
