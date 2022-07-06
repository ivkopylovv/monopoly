package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chance_card")
public class ChanceCard {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "description")
    private String description;

    @Column(name = "money_difference")
    private Long moneyDifference;

    @Column(name = "step")
    private Long step;
}
