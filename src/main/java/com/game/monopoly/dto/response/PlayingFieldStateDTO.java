package com.game.monopoly.dto.response;

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
public class PlayingFieldStateDTO {
    private List<PlayerDTO> players;
    private String state;
    private String currentPlayer;
    private String moveStatus;
    private Map<Long, CardStateDTO> cardStates;
    private List<ResultMessageDTO> chatHistory;
}
