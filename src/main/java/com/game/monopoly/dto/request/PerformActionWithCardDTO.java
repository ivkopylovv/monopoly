package com.game.monopoly.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformActionWithCardDTO {
    private String sessionId;
    private String playerName;
    private Long cardId;
}
