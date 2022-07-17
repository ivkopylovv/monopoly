package com.game.monopoly.controller;

import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CommonCard;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.Session;
import com.game.monopoly.mapper.PlayingFieldMapper;
import com.game.monopoly.service.CardStateService;
import com.game.monopoly.service.CommonCardService;
import com.game.monopoly.service.CompanyCardService;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.SESSION_WAS_CREATED;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SessionHttpController {
    private final SessionService sessionService;
    private final CommonCardService commonCardService;
    private final CompanyCardService companyCardService;
    private final CardStateService cardStateService;

    @PostMapping(value = "/sessions")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody InitializeSessionDTO dto) {
        sessionService.checkSessionExists(dto.getSessionId());
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        cardStateService.saveCardStates(cardStates);
        sessionService.saveSession(dto.getSessionId(), dto.getPlayerName(), dto.getColour(), cardStates);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/sessions/{sessionId}")
    public ResponseEntity<PlayingFieldDTO> getPlayingField(@PathVariable String sessionId) {
        Session session = sessionService.getSession(sessionId);
        List<CommonCard> cards = commonCardService.getAllCards();

        PlayingFieldDTO playingField = PlayingFieldMapper.buildPlayingField(session, cards);

        return ResponseEntity.ok().body(playingField);
    }
}
