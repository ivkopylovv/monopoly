package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.entity.CardState;

import java.util.HashMap;
import java.util.Map;

public class CardActionMapper {

    public static CardStatePlayerBalanceDTO cardActionTODTO(String playerName, Long balance, CardState cardState) {
        Map<Long, CardStateDTO> cardStateMap = new HashMap<>();
        cardStateMap.put(CardStateMapper.cardStateToCardId(cardState),
                CardStateMapper.cardStateEntityToDTO(cardState));

        return new CardStatePlayerBalanceDTO(
                PlayerMapper.playerBalanceToDTO(playerName, balance),
                cardStateMap
        );
    }
}
