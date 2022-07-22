package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.PlayingFieldStateDTO;
import com.game.monopoly.dto.response.PlayingFieldStaticDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CommonCard;
import com.game.monopoly.entity.Session;
import com.game.monopoly.helper.SortHelper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayingFieldMapper {

    public static PlayingFieldStateDTO buildPlayingFieldState(Session session) {

        return new PlayingFieldStateDTO()
                .setPlayers(PlayerMapper.entitiesToDTOList(SortHelper.getSortedPlayers(session.getPlayers())))
                .setState(String.valueOf(session.getState()))
                .setCurrentPlayer(session.getCurrentPlayer())
                .setMoveStatus(String.valueOf(session.getMoveStatus()))
                .setCardStates(PlayingFieldMapper.cardStatesEntitiesToDTOList(session.getCardStates()))
                .setChatHistory(ResultMessageMapper.entitiesToDTOList(session.getMessages()));
    }

    public static PlayingFieldStaticDTO buildPlayingFieldStatic(List<CommonCard> cards) {
        return new PlayingFieldStaticDTO(cards);
    }

    public static Map<Long, CardStateDTO> cardStatesEntitiesToDTOList(List<CardState> cardStates) {
        return cardStates
                .stream()
                .collect(Collectors.toMap(
                        CardStateMapper::cardStateToCardId,
                        CardStateMapper::cardStateEntityToDTO, (a, b) -> b)
                );
    }

}
