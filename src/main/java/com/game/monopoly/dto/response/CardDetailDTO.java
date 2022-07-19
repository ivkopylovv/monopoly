package com.game.monopoly.dto.response;

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
public class CardDetailDTO {
    private String title;
    private String sphere;
    private List<Long> fines;
    private Long price;
    private Long sale_price;
    private Long star_price;
    private String ownerName;
    private Integer collectionNumber;
}
