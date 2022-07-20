package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "non_type_card")
public class NonTypeCard {
    @Id
    private Long id;

    @Column(name = "money_diff", nullable = false)
    private Long moneyDiff;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NonTypeCard)) return false;
        NonTypeCard that = (NonTypeCard) o;
        return this.id != null && this.id.equals(that.id)
                && this.moneyDiff != null && this.moneyDiff.equals(that.moneyDiff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneyDiff);
    }
}
