package com.game.monopoly.mapper;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.*;

public class MessageContentMapper {

    public static String rollDiceToMessageContent(List<Integer> digits) {
        return String.format(DICE_ROLL_RESULT, digits.get(0), digits.get(1));
    }

    public static String jackpotEventWinToMessageContent(List<Integer> digits, int digit, Long benefit) {
        return String.format(JACKPOT_COMMON + JACKPOT_WIN, digits.toString(), digit, benefit);
    }

    public static String jackpotEventLoseToMessageContent(List<Integer> digits, int digit, Long loss) {
        return String.format(JACKPOT_COMMON + JACKPOT_LOSE, digits.toString(), digit, loss);
    }
}
