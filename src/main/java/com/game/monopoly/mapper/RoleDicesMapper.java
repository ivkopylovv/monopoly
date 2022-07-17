package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.PlayerPositionDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;

import java.util.List;

public class RoleDicesMapper {

    public static RollDiceResultDTO rollResultTODTO(List<Integer> digits, String playerName, int position) {
        return new RollDiceResultDTO()
                .setDigits(digits)
                .setPlayer(new PlayerPositionDTO(playerName, position));
    }
}
