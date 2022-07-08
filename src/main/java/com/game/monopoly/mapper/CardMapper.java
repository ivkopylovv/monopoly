package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.CommonCardDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.ChanceCard;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.NonTypeCard;
import com.game.monopoly.enums.CardType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardMapper {

    public static List<CommonCardDTO> allCardsToCommonsList(
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

        return resultList;
    }


    public static Map<Integer, List<CardStateDTO>> splitCompanyCardStatesOnCollections(List<CardState> cardStates) {
        Map<Integer, List<CardState>> cardStateMap = cardStates
                .stream()
                .collect(Collectors.groupingBy(CardMapper::apply));

        return cardStateMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                        entry.getValue()
                                .stream()
                                .map(CardMapper::cardStateToDTO)
                                .collect(Collectors.toList())));
    }

    private static Integer apply(CardState cardState) {
        return cardState.getCard().getCollectionNumber();
    }

    private static CardStateDTO cardStateToDTO(CardState cs) {
        return new CardStateDTO(
                cs.getCard().getId(),
                cs.getCard().getPrice(),
                cs.getCurrentFine(),
                cs.getOwnerName(),
                cs.getLevel()
        );
    }
}
