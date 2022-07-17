package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CommonCard;
import com.game.monopoly.entity.Session;
import com.game.monopoly.helper.SortHelper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayingFieldMapper {

    public static PlayingFieldDTO buildPlayingField(Session session, List<CommonCard> cards) {

        return new PlayingFieldDTO()
                .setPlayers(PlayerMapper.entitiesToDTOList(SortHelper.getSortedPlayers(session.getPlayers())))
                .setState(String.valueOf(session.getState()))
                .setCurrentPlayer(session.getCurrentPlayer())
                .setCards(cards)
                .setCardStates(PlayingFieldMapper.cardStatesEntitiesToDTOList(session.getCardStates()));
    }

    private static Map<Long, CardStateDTO> cardStatesEntitiesToDTOList(List<CardState> cardStates) {
        return cardStates
                .stream()
                .collect(Collectors.toMap(
                        CardStateMapper::cardStateToCardId,
                        CardStateMapper::cardStateEntityToDTO, (a, b) -> b)
                );
    }

}
