package com.game.monopoly.controller;

import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.request.RollDiceDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SessionProcessController {
    private final SessionService sessionService;
    private final PlayerService playerService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/sessions/add-player")
    public ResponseEntity<Player> addPlayer(InitializeSessionDTO dto) {
        Session session = sessionService.getSession(dto.getSessionId());
        Player player = playerService.savePlayer(dto.getPlayerName(), dto.getColour());
        sessionService.addPlayerToSession(session, player);
        simpMessagingTemplate.convertAndSend("/topic/add-player/" + dto.getSessionId(), player);

        return ResponseEntity.ok().body(player);
    }

    @MessageMapping(value = "/sessions/roll-dice")
    public ResponseEntity<RollDiceResultDTO> randomSteps(RollDiceDTO dto) {
        Player player = playerService.getPlayer(dto.getPlayerName());
        RollDiceResultDTO rollDiceResult = playerService.rollDices(player);
        simpMessagingTemplate.convertAndSend("/topic/roll-dice/" + dto.getSessionId(), rollDiceResult);

        return ResponseEntity.ok().body(rollDiceResult);
    }
}
