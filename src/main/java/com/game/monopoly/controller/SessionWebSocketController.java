package com.game.monopoly.controller;

import com.game.monopoly.dto.request.*;
import com.game.monopoly.dto.response.*;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.mapper.MessageContentMapper;
import com.game.monopoly.mapper.PlayerMapper;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.game.monopoly.constants.ResultMessage.*;
import static com.game.monopoly.enums.SessionState.IN_PROGRESS;

@Controller
@RequiredArgsConstructor
public class SessionWebSocketController {
    private final SessionService sessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/sessions/add-player")
    public ResponseEntity<PlayerDTO> addPlayer(InitializeSessionDTO dto) {
        Player player = sessionService.addPlayerToSession(dto.getSessionId(), dto.getPlayerName(), dto.getColour());
        PlayerDTO playerDTO = PlayerMapper.entityToPLayerDTO(player);
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), NEW_PLAYER);

        simpMessagingTemplate.convertAndSend("/topic/add-player/" + dto.getSessionId(), playerDTO);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(playerDTO);
    }

    @MessageMapping(value = "/sessions/roll-dice")
    public ResponseEntity<RollDiceResultDTO> getNewPlayerPosition(SessionPlayerNameDTO dto) {
        RollDiceResultDTO rollDiceResult = sessionService.rollDices(dto.getSessionId(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(
                dto.getPlayerName(),
                MessageContentMapper.rollDiceToMessageContent(rollDiceResult.getDigits())
        );

        simpMessagingTemplate.convertAndSend("/topic/roll-dice/" + dto.getSessionId(), rollDiceResult);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(rollDiceResult);
    }

    @MessageMapping(value = "/sessions/start-game")
    public ResponseEntity<StartGameResultDTO> startGame(ChangeCurrentPlayerDTO dto) {
        sessionService.startGame(dto.getSessionId(), dto.getPlayerName());

        StartGameResultDTO result = new StartGameResultDTO(IN_PROGRESS.toString(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(null, GAME_WAS_STARTED);

        simpMessagingTemplate.convertAndSend("/topic/start-game/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(result);
    }

    @MessageMapping(value = "/sessions/move-transition")
    public ResponseEntity<CurrentPlayerDTO> moveTransition(ChangeCurrentPlayerDTO dto) {
        String currentPlayer = sessionService.getNextPlayer(dto.getSessionId(), dto.getPlayerName());
        CurrentPlayerDTO result = new CurrentPlayerDTO(currentPlayer);

        simpMessagingTemplate.convertAndSend("/topic/move-transition/" + dto.getSessionId(), result);

        return ResponseEntity.ok().body(result);
    }

    @MessageMapping(value = "/sessions/buy-card")
    public ResponseEntity<BuyCardDTO> buyCard(PerformActionWithCardDTO dto) {
        BuyCardDTO result = sessionService.buyCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), BUY_CARD);

        simpMessagingTemplate.convertAndSend("/topic/buy-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(result);
    }

    @MessageMapping(value = "/sessions/pay-for-card")
    public ResponseEntity<PayForCardDTO> payForCard(PerformActionWithCardDTO dto) {
        PayForCardDTO result = sessionService.payForCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), PAY_FOR_CARD);

        simpMessagingTemplate.convertAndSend("/topic/pay-for-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(result);
    }

    @MessageMapping(value = "/sessions/move-status")
    public ResponseEntity<MoveStatusDTO> getCurrentMoveStatus(SessionIdDTO dto) {
        MoveStatus status = sessionService.getCurrentMoveStatus(dto.getSessionId());
        MoveStatusDTO result = new MoveStatusDTO(String.valueOf(status));

        simpMessagingTemplate.convertAndSend("/topic/move-status/" + dto.getSessionId(), result);

        return ResponseEntity.ok().body(result);
    }
}
