package com.game.monopoly.helper;

import com.game.monopoly.entity.CardState;
import com.game.monopoly.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;

import static com.game.monopoly.constants.ErrorMessage.CARD_NOT_FOUND;

public class FindHelper {

    public static CardState findCardStateByCardId(List<CardState> cardStates, Long cardId) {
        return cardStates
                .stream()
                .filter(cs -> Objects.equals(cs.getCard().getId(), cardId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
    }

}
