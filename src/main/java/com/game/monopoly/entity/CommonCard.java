package com.game.monopoly.entity;

import com.game.monopoly.enums.CardType;
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
@Table(name = "common_card")
public class CommonCard {
    @Id
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "card_type", nullable = false)
    @Enumerated(STRING)
    private CardType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonCard)) return false;
        CommonCard that = (CommonCard) o;
        return this.id != null && this.id.equals(that.id)
                && this.image != null && this.image.equals(that.image)
                && this.type != null && this.type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, type);
    }
}
