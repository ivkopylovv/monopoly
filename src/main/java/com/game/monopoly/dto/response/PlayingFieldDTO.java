package com.game.monopoly.dto.response;

import com.game.monopoly.entity.CommonCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayingFieldDTO {
    private List<PlayerDTO> players;
    private String state;
    private String currentPlayer;
    private List<CommonCard> cards;
    private Map<Long, CardStateDTO> cardStates;
}
