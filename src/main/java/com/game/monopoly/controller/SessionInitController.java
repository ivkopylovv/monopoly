package com.game.monopoly.controller;

import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.mapper.PlayingFieldMapper;
import com.game.monopoly.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.SESSION_WAS_CREATED;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SessionInitController {
    private final SessionService sessionService;
    private final CompanyCardService companyCardService;
    private final ChanceCardService chanceCardService;
    private final NonTypeCardService nonTypeCardService;
    private final PlayerService playerService;
    private final CardStateService cardStateService;

    @PostMapping(value = "/sessions")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody InitializeSessionDTO dto) {
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);

        Player player = playerService.savePlayer(dto.getPlayerName(), dto.getColour());
        cardStateService.saveCardStates(cardStates);
        sessionService.saveSession(dto.getSessionId(), player, cardStates);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/sessions/{sessionId}")
    public ResponseEntity<PlayingFieldDTO> getPlayingField(@PathVariable String sessionId) {
        Session session = sessionService.getSession(sessionId);
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<ChanceCard> chanceCards = chanceCardService.getChanceCards();
        List<NonTypeCard> nonTypeCards = nonTypeCardService.getNonTypeCards();

        PlayingFieldDTO playingField = PlayingFieldMapper
                .buildPlayingField(session, companyCards, chanceCards, nonTypeCards);

        return ResponseEntity.ok().body(playingField);
    }
}
