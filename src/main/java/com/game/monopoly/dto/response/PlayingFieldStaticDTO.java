package com.game.monopoly.dto.response;

import com.game.monopoly.entity.CommonCard;
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
public class PlayingFieldStaticDTO {
    private List<CommonCard> cards;
}
