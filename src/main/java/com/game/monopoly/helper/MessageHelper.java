package com.game.monopoly.helper;

import com.game.monopoly.entity.Message;
import com.game.monopoly.mapper.MessageContentMapper;

import java.util.List;

import static com.game.monopoly.constants.ResultMessage.*;
import static com.game.monopoly.enums.MessageType.COMMON;

public class MessageHelper {

    public static Message createAddPlayerMessage(String playerName) {
        return new Message()
                .setContent(NEW_PLAYER)
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createRollDicesMessage(String playerName, List<Integer> digits) {
        return new Message()
                .setContent(MessageContentMapper.rollDiceToMessageContent(digits))
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createStartGameMessage() {
        return new Message()
                .setContent(GAME_WAS_STARTED)
                .setSender(null)
                .setType(COMMON);
    }

    public static Message createBuyCardMessage(String playerName) {
        return new Message()
                .setContent(BUY_CARD)
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createSentMessage(String content, String playerName) {
        return new Message()
                .setContent(content)
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createPayForCardMessage(String playerName) {
        return new Message()
                .setContent(PAY_FOR_CARD)
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createImproveCardMessage(String playerName) {
        return new Message()
                .setContent(IMPROVE_CARD)
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createSellCardMessage(String playerName) {
        return new Message()
                .setContent(IMPROVE_CARD)
                .setSender(playerName)
                .setType(COMMON);
    }

    public static Message createLowerCardLevelMessage(String playerName) {
        return new Message()
                .setContent(LOWER_CARD_LEVEL)
                .setSender(playerName)
                .setType(COMMON);
    }
}
