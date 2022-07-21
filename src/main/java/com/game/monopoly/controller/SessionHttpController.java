package com.game.monopoly.controller;

import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.CardStateService;
import com.game.monopoly.service.CompanyCardService;
import com.game.monopoly.service.SessionCommonService;
import com.game.monopoly.service.SessionHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SessionHttpController {
    private final SessionHttpService sessionHttpService;
    private final SessionCommonService sessionCommonService;
    private final CompanyCardService companyCardService;
    private final CardStateService cardStateService;

    @PostMapping(value = "/sessions")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody InitializeSessionDTO dto) {
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        cardStateService.saveCardStates(cardStates);
        sessionHttpService.saveSession(dto.getSessionId(), dto.getPlayerName(), dto.getColour(), cardStates);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/sessions/check/{sessionId}")
    public ResponseEntity<SuccessMessageDTO> isSessionValid(@PathVariable String sessionId) {
        sessionCommonService.checkSessionExists(sessionId);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_IS_VALID));
    }

    @GetMapping(value = "/sessions/{sessionId}")
    public ResponseEntity<PlayingFieldDTO> getPlayingField(@PathVariable String sessionId) {
        PlayingFieldDTO playingField = sessionHttpService.getPlayingField(sessionId);

        return ResponseEntity.ok().body(playingField);
    }

    @DeleteMapping(value = "/sessions/{id}")
    public ResponseEntity<SuccessMessageDTO> finishSession(@PathVariable String id) {
        sessionHttpService.finishSession(id);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_DELETED));
    }

}
