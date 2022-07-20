package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "level_fine")
public class LevelFine {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private Long value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LevelFine)) return false;
        LevelFine levelFine = (LevelFine) o;
        return this.id != null && this.id.equals(levelFine.id)
                && this.value != null && this.value.equals(levelFine.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
