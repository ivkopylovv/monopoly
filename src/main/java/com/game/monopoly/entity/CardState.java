package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card_state")
public class CardState {
    @Id
    private Long id;

    @Column(name = "current_fine")
    private Long currentFine;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "ownerName")
    private String ownerName;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "card_id", nullable = false)
    private CompanyCard card;
}
