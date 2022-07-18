package com.game.monopoly.controller;

import com.game.monopoly.dto.request.SessionPlayerNameDTO;
import com.game.monopoly.dto.response.PlayerBalanceDTO;
import com.game.monopoly.dto.response.PlayerPositionDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.entity.ChanceCard;
import com.game.monopoly.entity.Player;
import com.game.monopoly.helper.PlayerPositionHelper;
import com.game.monopoly.mapper.PlayerMapper;
import com.game.monopoly.service.ChanceCardService;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.game.monopoly.enums.ChanceCardType.CHANGE_BALANCE;

@Controller
@RequiredArgsConstructor
public class ChanceCardController {
    private final ChanceCardService chanceCardService;
    private final PlayerService playerService;
    private final SessionService sessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/cards/chance")
    public void getChanceCard(SessionPlayerNameDTO dto) {
        ChanceCard card = chanceCardService.getRandomChanceCard();

        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();
        Player player = playerService.getPlayer(sessionId, playerName);

        if (card.getType() == CHANGE_BALANCE) {
            Long newBalance = player.getBalance() + card.getMoneyDifference();
            playerService.updatePlayerBalance(newBalance, sessionId, playerName);

            PlayerBalanceDTO result = PlayerMapper.playerBalanceToDTO(playerName, newBalance);
            simpMessagingTemplate.convertAndSend("/topic/change-balance/" + sessionId, result);
        } else {
            int newPosition = PlayerPositionHelper.getNewPosition(player.getPosition(), card.getStep());
            playerService.updatePlayerPosition(newPosition, sessionId, playerName);

            PlayerPositionDTO result = PlayerMapper.playerPositionToDTO(playerName, newPosition);
            simpMessagingTemplate.convertAndSend("/topic/change-position/" + sessionId, result);
        }

        String description = card.getDescription();
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, description);
        sessionService.addCommonMessageToChatHistory(sessionId, playerName, description);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }
}
