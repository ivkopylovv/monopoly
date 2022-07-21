package com.game.monopoly.dto.response;

import com.game.monopoly.enums.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private Integer position;
    private String role;
    private String colour;
    private Long balance;
    private PlayerStatus status;
}
