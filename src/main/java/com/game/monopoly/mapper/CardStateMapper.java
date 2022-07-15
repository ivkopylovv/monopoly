package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.entity.CardState;

public class CardStateMapper {

    public static CardStateDTO cardStateEntityToDTO(CardState cs) {
        return new CardStateDTO(
                cs.getCard().getPrice(),
                cs.getCurrentFine(),
                cs.getOwnerName(),
                cs.getLevel(),
                cs.getCard().getCollectionNumber());
    }

    public static Long cardStateToCardId(CardState cardState) {
        return cardState.getCard().getId();
    }
}
