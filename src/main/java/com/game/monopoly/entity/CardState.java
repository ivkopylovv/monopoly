package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

import static com.game.monopoly.constants.InitialGameValue.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card_state")
public class CardState {
    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardState)) return false;
        CardState cardState = (CardState) o;
        return this.id != null && this.id.equals(cardState.id)
                && this.currentFine != null && this.currentFine.equals(cardState.currentFine)
                && this.level != null && this.level.equals(cardState.level)
                && this.ownerName != null && this.ownerName.equals(cardState.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentFine, level, ownerName, card);
    }
}
