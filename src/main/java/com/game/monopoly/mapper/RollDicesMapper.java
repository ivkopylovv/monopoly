package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.MoveResultDTO;
import com.game.monopoly.dto.response.PlayerBalanceDTO;
import com.game.monopoly.dto.response.PlayerPositionDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;

import java.util.List;

public class RollDicesMapper {

    public static RollDiceResultDTO rollResultTODTO(List<Integer> digits, String playerName, int position, Long balance) {

        return new RollDiceResultDTO()
                .setPlayerBalance(new PlayerBalanceDTO(playerName, balance))
                .setDigits(digits)
                .setPlayer(new PlayerPositionDTO(playerName, position));
    }

    public static MoveResultDTO rollResultToMoveDTO(RollDiceResultDTO dto) {

        return new MoveResultDTO()
                .setDigits(dto.getDigits())
                .setPlayer(dto.getPlayer());
    }
}
