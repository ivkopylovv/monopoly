package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "office_card")
public class OfficeCard {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "card_number", nullable = false)
    private Long number;

    @Column(name = "description")
    private String description;

    @Column(name = "default_price", nullable = false)
    private Long defaultPrice;

    @Column(name = "current_fine")
    private Long currentFine;

    @Column(name = "house_price", nullable = false)
    private Long housePrice;

    @Column(name = "level", nullable = false)
    private Long level;

    @ManyToMany(fetch = LAZY)
    @JoinColumn(name = "level_prices")
    private List<LevelPrice> prices = new ArrayList<>();

}
