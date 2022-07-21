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
@AllArgsConstructor
@NoArgsConstructor
public class OfferAcceptDTO {
    private PlayerBalanceDTO creator;
    private PlayerBalanceDTO receiver;
    private Map<Long, CardStateDTO> cardStates;
}
