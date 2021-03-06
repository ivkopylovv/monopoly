package com.game.monopoly.controller;

import com.game.monopoly.dto.response.CardDetailDTO;
import com.game.monopoly.service.CompanyCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CompanyCardController {
    private final CompanyCardService companyCardService;

    @GetMapping(value = "/cards", params = {"sessionId", "cardId"})
    public ResponseEntity<CardDetailDTO> getDetailedCard(
            @RequestParam("sessionId") String sessionId,
            @RequestParam("cardId") Long cardId) {
        CardDetailDTO result = companyCardService.getDetailedCardInfo(sessionId, cardId);

        return ResponseEntity.ok().body(result);
    }
}
