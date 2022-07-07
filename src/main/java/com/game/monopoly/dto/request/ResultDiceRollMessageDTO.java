package com.game.monopoly.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDiceRollMessageDTO {
    private String message;
    private Long firstRollResult;
    private Long secondRollResult;
}
