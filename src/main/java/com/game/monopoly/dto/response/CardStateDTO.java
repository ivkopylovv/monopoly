package com.game.monopoly.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardStateDTO {
    private Long id;
    private Long price;
    private Long fine;
    private String ownerName;
    private Integer level;
}
