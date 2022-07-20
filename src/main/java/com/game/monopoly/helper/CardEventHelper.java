package com.game.monopoly.helper;

import java.util.List;

import static com.game.monopoly.constants.EventParam.*;

public class CardEventHelper {

    public static boolean isArrayContainNumber(List<Integer> digits, int digit) {
        return digits
                .stream()
                .anyMatch(d -> d == digit);
    }

    public static Long getJackpotBenefit(int count) {
        return (JACKPOT_MAX_DIGITS_COUNT + 1 - count)
                * JACKPOT_BET * JACKPOT_MULTIPLIER;
    }
}
