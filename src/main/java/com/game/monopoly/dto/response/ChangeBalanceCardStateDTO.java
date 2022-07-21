package com.game.monopoly.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBalanceCardStateDTO {
    private PlayerBalanceDTO player;
    private Map<Long, CardStateDTO> cardStates;
}
