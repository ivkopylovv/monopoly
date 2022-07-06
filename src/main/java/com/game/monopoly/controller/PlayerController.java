package com.game.monopoly.controller;

import com.game.monopoly.dto.request.ChangeBalanceDTO;
import com.game.monopoly.dto.request.PerformActionWithCardDTO;
import com.game.monopoly.dto.request.ResultMessageDTO;
import com.game.monopoly.mapper.ResultMessageMapper;
import com.game.monopoly.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PutMapping(value = "/players/change-balance")
    public ResponseEntity<ResultMessageDTO> changePlayerBalance(@RequestBody ChangeBalanceDTO dto) {
        playerService.changePlayerBalance(dto.getPlayerName(), dto.getMoneyDifference());

        return ResponseEntity.ok().body(ResultMessageMapper.changeBalanceDTOToResultMessage(dto));
    }

    @PutMapping(value = "/players/buy-card")
    public ResponseEntity<ResultMessageDTO> buyCard(@RequestBody PerformActionWithCardDTO dto) {
        playerService.buyCard(dto.getPlayerName(), dto.getCardNumber());

        return ResponseEntity.ok().body(ResultMessageMapper.buyCardDTOToResultMessage(dto));
    }

    @PutMapping(value = "/players/pay-for-card")
    public ResponseEntity<ResultMessageDTO> payForCard(@RequestBody PerformActionWithCardDTO dto) {
        playerService.payForCard(dto.getPlayerName(), dto.getCardNumber());

        return ResponseEntity.ok().body(ResultMessageMapper.payForCardDTOToResultMessage(dto));
    }
}
