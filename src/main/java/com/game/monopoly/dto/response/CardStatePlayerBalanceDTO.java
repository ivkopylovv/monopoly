package com.game.monopoly.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardStatePlayerBalanceDTO {
    private PlayerBalanceDTO player;
    private Map<Long, CardStateDTO> cardState;
}
