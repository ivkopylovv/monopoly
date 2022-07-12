package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static com.game.monopoly.constants.InitialGameValue.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card_state")
public class CardState {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "current_fine")
    private Long currentFine = INITIAL_CARD_FINE;

    @Column(name = "level", nullable = false)
    private Integer level = INITIAL_CARD_LEVEL;

    @Column(name = "ownerName")
    private String ownerName = INITIAL_CARD_OWNER_NAME;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "card_id", nullable = false)
    private CompanyCard card;
}
