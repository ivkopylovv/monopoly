package com.game.monopoly.entity;

import com.game.monopoly.enums.ChanceCardType;
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
@Table(name = "chance_card")
public class ChanceCard {
    @Id
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "money_difference")
    private Long moneyDifference;

    @Column(name = "step")
    private Integer step;

    @Column(name = "type", nullable = false)
    @Enumerated(STRING)
    private ChanceCardType type;
}
