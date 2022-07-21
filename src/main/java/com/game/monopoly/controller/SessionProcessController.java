package com.game.monopoly.controller;

import com.game.monopoly.dto.request.GameEventDTO;
import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.request.SessionIdDTO;
import com.game.monopoly.dto.request.SessionPlayerNameDTO;
import com.game.monopoly.dto.response.*;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.mapper.MessageContentMapper;
import com.game.monopoly.mapper.PlayerMapper;
import com.game.monopoly.mapper.RollDicesMapper;
import com.game.monopoly.service.SessionProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.game.monopoly.constants.ResultMessage.*;
import static com.game.monopoly.enums.SessionState.IN_PROGRESS;

@Controller
@RequiredArgsConstructor
public class SessionProcessController {
    private final SessionProcessService sessionProcessService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/sessions/add-player")
    public void addPlayer(InitializeSessionDTO dto) {
        Player player = sessionProcessService.addPlayerToSession(dto.getSessionId(), dto.getPlayerName(), dto.getColour());
        PlayerDTO playerDTO = PlayerMapper.entityToPLayerDTO(player);
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), NEW_PLAYER);

        simpMessagingTemplate.convertAndSend("/topic/add-player/" + dto.getSessionId(), playerDTO);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/roll-dice")
    public void getNewPlayerPosition(SessionPlayerNameDTO dto) {
        RollDiceResultDTO rollDiceResult = sessionProcessService.rollDices(dto.getSessionId(), dto.getPlayerName());
        Long balance = rollDiceResult.getPlayerBalance().getBalance();

        if (balance != null) {
            ResultMessageDTO startMessage = new ResultMessageDTO(dto.getPlayerName(), START_BONUS_PASS);
            simpMessagingTemplate.convertAndSend(
                    "/topic/change-balance/" + dto.getSessionId(),
                    rollDiceResult.getPlayerBalance());
            simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), startMessage);
        }

        ResultMessageDTO resultMessage = new ResultMessageDTO(
                dto.getPlayerName(),
                MessageContentMapper.rollDiceToMessageContent(rollDiceResult.getDigits())
        );
        MoveResultDTO result = RollDicesMapper.rollResultToMoveDTO(rollDiceResult);

        simpMessagingTemplate.convertAndSend("/topic/roll-dice/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/start-game")
    public void startGame(GameEventDTO dto) {
        sessionProcessService.startGame(dto.getSessionId(), dto.getPlayerName());

        StartGameResultDTO result = new StartGameResultDTO(IN_PROGRESS.toString(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(null, GAME_WAS_STARTED);

        simpMessagingTemplate.convertAndSend("/topic/start-game/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/move-transition")
    public String moveTransition(GameEventDTO dto) {
        String currentPlayer = sessionProcessService.getNextPlayer(dto.getSessionId(), dto.getPlayerName());
        CurrentPlayerDTO result = new CurrentPlayerDTO(currentPlayer);

        simpMessagingTemplate.convertAndSend("/topic/move-transition/" + dto.getSessionId(), result);

        return currentPlayer;
    }

    @MessageMapping(value = "/sessions/move-status")
    public void getCurrentMoveStatus(SessionIdDTO dto) {
        MoveStatus status = sessionProcessService.getCurrentMoveStatus(dto.getSessionId());
        MoveStatusDTO result = new MoveStatusDTO(String.valueOf(status));

        simpMessagingTemplate.convertAndSend("/topic/move-status/" + dto.getSessionId(), result);
    }

    @MessageMapping(value = "/sessions/surrender")
    public void surrenderTheGame(GameEventDTO dto) {
        PlayerStatusDTO result = sessionProcessService.getSurrenderPlayer(dto.getSessionId(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), SURRENDER);

        simpMessagingTemplate.convertAndSend("/topic/surrender/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }
}
