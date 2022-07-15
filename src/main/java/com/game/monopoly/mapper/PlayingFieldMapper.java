package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.CommonCardDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.enums.CardType;

import java.util.*;
import java.util.stream.Collectors;

public class PlayingFieldMapper {

    public static PlayingFieldDTO buildPlayingField(
            Session session,
            List<CompanyCard> companyCards,
            List<ChanceCard> chanceCards,
            List<NonTypeCard> nonTypeCards) {

        return new PlayingFieldDTO()
                .setPlayers(session.getPlayers())
                .setState(String.valueOf(session.getState()))
                .setCards(PlayingFieldMapper.allCardsToCommonsList(companyCards, chanceCards, nonTypeCards))
                .setCardStates(PlayingFieldMapper.cardStatesEntitiesToDTOList(session.getCardStates()));
    }

    private static List<CommonCardDTO> allCardsToCommonsList(
            List<CompanyCard> companyCards,
            List<ChanceCard> chanceCards,
            List<NonTypeCard> nonTypeCards) {
        List<CommonCardDTO> resultList = new ArrayList<>();

        companyCards.forEach(
                (card) -> resultList.add(
                        new CommonCardDTO(
                                card.getId(),
                                card.getImage(),
                                String.valueOf(CardType.COMPANY)
                        )
                )
        );
        chanceCards.forEach(
                (card) -> resultList.add(
                        new CommonCardDTO(
                                card.getId(),
                                card.getImage(),
                                String.valueOf(CardType.CHANCE)
                        )
                )
        );
        nonTypeCards.forEach(
                (card) -> resultList.add(
                        new CommonCardDTO(
                                card.getId(),
                                card.getImage(),
                                String.valueOf(CardType.NONTYPE)
                        )
                )
        );

        return resultList
                .stream()
                .sorted(Comparator.comparing(CommonCardDTO::getId))
                .collect(Collectors.toList());
    }

    private static Map<Long, CardStateDTO> cardStatesEntitiesToDTOList(List<CardState> cardStates) {
        return cardStates
                .stream()
                .collect(Collectors.toMap(
                        PlayingFieldMapper::cardStateToCardId,
                        PlayingFieldMapper::cardStateEntityToDTO, (a, b) -> b)
                );
    }

    private static CardStateDTO cardStateEntityToDTO(CardState cs) {
        return new CardStateDTO(
                cs.getCard().getPrice(),
                cs.getCurrentFine(),
                cs.getOwnerName(),
                cs.getLevel(),
                cs.getCard().getCollectionNumber());
    }

    private static Long cardStateToCardId(CardState cardState) {
        return cardState.getCard().getId();
    }
}
