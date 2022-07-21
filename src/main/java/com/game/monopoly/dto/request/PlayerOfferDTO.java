package com.game.monopoly.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerOfferDTO {
    private String playerName;
    private Long money;
    private List<Long> cards;
}
