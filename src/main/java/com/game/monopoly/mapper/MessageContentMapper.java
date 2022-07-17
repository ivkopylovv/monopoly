package com.game.monopoly.mapper;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.DICE_ROLL_RESULT;

public class MessageContentMapper {

    public static String rollDiceToMessageContent(List<Integer> digits) {
        return String.format(DICE_ROLL_RESULT, digits.get(0), digits.get(1));
    }

   /* public static ResultMessageDTO changeBalanceDTOToResultMessage(ChangeBalanceDTO dto) {
        Long diff = dto.getMoneyDifference();
        String format = diff > 0 ? UPDATE_BALANCE_POSITIVE : UPDATE_BALANCE_NEGATIVE;

        return new ResultMessageDTO(
                dto.getPlayerName(),
                String.format(format, dto.getPlayerName(), dto.getMoneyDifference())
        );
    }*/
}
