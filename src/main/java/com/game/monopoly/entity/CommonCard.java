package com.game.monopoly.entity;

import com.game.monopoly.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "common_card")
public class CommonCard {
    @Id
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "card_type", nullable = false)
    @Enumerated(STRING)
    private CardType type;
}
