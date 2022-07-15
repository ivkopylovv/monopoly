package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.BuyCardDTO;
import com.game.monopoly.dto.response.CardStateDTO;
import com.game.monopoly.dto.response.PlayerBalanceDTO;
import com.game.monopoly.entity.CardState;

import java.util.HashMap;
import java.util.Map;

public class CardActionMapper {

    public static BuyCardDTO cardActionTODTO(String playerName, Long balance, CardState cardState) {
        Map<Long, CardStateDTO> cardStateMap = new HashMap<>();
        cardStateMap.put(CardStateMapper.cardStateToCardId(cardState),
                CardStateMapper.cardStateEntityToDTO(cardState));

        return new BuyCardDTO(
                CardActionMapper.playerBalanceToDTO(playerName, balance),
                cardStateMap
        );
    }

    private static PlayerBalanceDTO playerBalanceToDTO(String playerName, Long balance) {
        return new PlayerBalanceDTO(playerName, balance);
    }
}
