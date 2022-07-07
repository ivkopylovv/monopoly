package com.game.monopoly.controller;

import com.game.monopoly.dto.request.EnterSessionDTO;
import com.game.monopoly.dto.request.PerformActionWithSessionDTO;
import com.game.monopoly.dto.request.ResultDiceRollMessageDTO;
import com.game.monopoly.dto.request.ResultMessageDTO;
import com.game.monopoly.mapper.ResultMessageMapper;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping(value = "/sessions/create-session")
    public ResponseEntity<EnterSessionDTO> createSession(@RequestBody PerformActionWithSessionDTO playerDTO) {

        String sessionId = sessionService.createSession(playerDTO.getPlayerName());

        return ResponseEntity.ok().body(new EnterSessionDTO(sessionId));
    }

    @PostMapping(value = "/sessions/add-player")
    public ResponseEntity<ResultMessageDTO> addPlayer(@RequestBody PerformActionWithSessionDTO playerDTO) {
        sessionService.addPlayer(playerDTO.getSessionId(), playerDTO.getPlayerName());

        return ResponseEntity.ok(ResultMessageMapper.addPlayerMessage(playerDTO));
    }


    @GetMapping(value = "/sessions/random-step")
    public ResponseEntity<ResultDiceRollMessageDTO> randomSteps(@RequestBody PerformActionWithSessionDTO playerDTO) {

        List<Long> rollResults = sessionService.randomSteps(playerDTO.getSessionId(), playerDTO.getPlayerName());

        return ResponseEntity.ok(ResultMessageMapper.randomSteps(playerDTO, rollResults));
    }
}
