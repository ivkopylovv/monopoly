package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.PlayerPositionDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.DICE_ROLL_RESULT;

public class RoleDicesMapper {

    public static RollDiceResultDTO rollResultTODTO(List<Integer> digits, String playerName, int position) {
        return new RollDiceResultDTO()
                .setDigits(digits)
                .setPlayer(new PlayerPositionDTO(playerName, position))
                .setMessage(String.format(DICE_ROLL_RESULT, playerName, digits.get(0), digits.get(1)));
    }
}
