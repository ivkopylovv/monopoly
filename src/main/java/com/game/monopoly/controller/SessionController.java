package com.game.monopoly.controller;

import com.game.monopoly.dto.request.ActionWithSessionDTO;
import com.game.monopoly.dto.request.ResultMessageDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.mapper.ResultMessageMapper;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import static com.game.monopoly.constants.ResultMessage.SESSION_WAS_CREATED;
import static com.game.monopoly.constants.WebSocketPath.GAME_PROCESS_PATH;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping(value = "/sessions/create")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody ActionWithSessionDTO dto) {
        sessionService.createSession(dto.getSessionId(), dto.getPlayerName());

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/sessions/{sessionId}")
    public ResponseEntity<PlayingFieldDTO> getPlayingField(@PathVariable String sessionId) {
        return ResponseEntity.ok().body(sessionService.getPlayingField(sessionId));
    }

    @PostMapping(value = "/sessions/add-player")
    public ResponseEntity<ResultMessageDTO> addPlayer(@RequestBody ActionWithSessionDTO dto) {
        sessionService.addPlayer(dto.getSessionId(), dto.getPlayerName());
        ResultMessageDTO resultMessage = ResultMessageMapper.addPlayerToResultMessage(dto);
        simpMessagingTemplate.convertAndSend(GAME_PROCESS_PATH + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(resultMessage);
    }

    @GetMapping(value = "/sessions/roll-dice")
    public ResponseEntity<RollDiceResultDTO> randomSteps(@RequestBody ActionWithSessionDTO dto) {
        RollDiceResultDTO rollDiceResult = sessionService.rollDices(dto.getSessionId(), dto.getPlayerName());
        simpMessagingTemplate.convertAndSend(GAME_PROCESS_PATH + dto.getSessionId(), rollDiceResult);

        return ResponseEntity.ok().body(rollDiceResult);
    }
}
