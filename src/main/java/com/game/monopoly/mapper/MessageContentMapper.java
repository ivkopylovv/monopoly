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

    public static String taxIncomeEventToMessageContent(Long taxIncome) {
        return String.format(TAX_INCOME_RESULT, taxIncome);
    }

    public static String taxLuxuryEventToMessageContent(Long taxLuxury) {
        return String.format(TAX_LUXURY_RESULT, taxLuxury);
    }

    public static String startBonusEventToMessageContent(Long startBonus) {
        return String.format(START_BONUS_RESULT, startBonus);
    }

    public static String teleportEventToMessageContent(Integer position) {
        return String.format(TELEPORT_RESULT, position);
    }

    public static String sendOfferToMessageContent(String receiver) {
        return String.format(OFFER_SEND, receiver);
    }
}
