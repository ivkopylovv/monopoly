package com.game.monopoly.dto.response;

import com.game.monopoly.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayingFieldDTO {
    private List<Player> players;
    private String state;
    private List<CommonCardDTO> cards;
    private Map<String, CardStateDTO> card_states;
}
