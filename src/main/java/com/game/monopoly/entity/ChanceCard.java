package com.game.monopoly.entity;

import com.game.monopoly.enums.ChanceCardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChanceCard)) return false;
        ChanceCard that = (ChanceCard) o;
        return this.id != null && this.id.equals(that.id)
                && this.description != null && this.description.equals(that.description)
                && Objects.equals(this.moneyDifference, that.moneyDifference)
                && Objects.equals(this.step, that.step)
                && this.type != null && this.type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, moneyDifference, step, type);
    }
}
