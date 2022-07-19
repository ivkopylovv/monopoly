package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardDetailDTO;
import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.LevelFine;

import java.util.List;
import java.util.stream.Collectors;

public class CardStateMapper {

    public static CardDetailDTO cardStateTOCardDetailDTO(CardState cardState) {
        CompanyCard card = cardState.getCard();

        return new CardDetailDTO(
                card.getTitle(),
                card.getSphere(),
                CardStateMapper.levelFinesToFines(card.getFines()),
                card.getPrice(),
                card.getSalePrice(),
                card.getStarPrice(),
                cardState.getOwnerName(),
                card.getCollectionNumber()
        );
    }

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

    private static List<Long> levelFinesToFines(List<LevelFine> levelFines) {
        return levelFines
                .stream()
                .map(LevelFine::getValue)
                .collect(Collectors.toList());
    }
}
