package com.game.monopoly.controller;

import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.request.ResultMessageDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.mapper.ResultMessageMapper;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import static com.game.monopoly.constants.ResultMessage.SESSION_WAS_CREATED;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping(value = "/sessions/create")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody InitializeSessionDTO dto) {
        sessionService.createSession(dto.getSessionId(), dto.getPlayerName(), dto.getColour());

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/sessions/{sessionId}")
    public ResponseEntity<PlayingFieldDTO> getPlayingField(@PathVariable String sessionId) {
        return ResponseEntity.ok().body(sessionService.getPlayingField(sessionId));
    }

    @MessageMapping(value = "/sessions/add-player")
    public ResponseEntity<ResultMessageDTO> addPlayer(InitializeSessionDTO dto) {
        sessionService.addPlayer(dto.getSessionId(), dto.getPlayerName(), dto.getColour());
        ResultMessageDTO resultMessage = ResultMessageMapper.addPlayerToResultMessage(dto);
        simpMessagingTemplate.convertAndSend("/topic/add-player" + dto.getSessionId(), resultMessage);

        return ResponseEntity.ok().body(resultMessage);
    }


    @MessageMapping(value = "/sessions/roll-dice")
    public ResponseEntity<RollDiceResultDTO> randomSteps(InitializeSessionDTO dto) {
        RollDiceResultDTO rollDiceResult = sessionService.rollDices(dto.getSessionId(), dto.getPlayerName());
        simpMessagingTemplate.convertAndSend("/topic/roll-dice/" + dto.getSessionId(), rollDiceResult);

        return ResponseEntity.ok().body(rollDiceResult);
    }
}
