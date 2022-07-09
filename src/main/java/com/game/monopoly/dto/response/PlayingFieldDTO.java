package com.game.monopoly.dto.response;

import com.game.monopoly.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayingFieldDTO {
    private List<Player> players;
    private String state;
    private List<CommonCardDTO> cards;
    private List<CardStateDTO> cardStates;
}
