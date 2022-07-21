package com.game.monopoly.entity;

import com.game.monopoly.entity.embedded.PlayerUniqueName;
import com.game.monopoly.enums.PlayerColour;
import com.game.monopoly.enums.PlayerRole;
import com.game.monopoly.enums.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_BALANCE;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_PLAYER_STATUS;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Embedded
    private PlayerUniqueName uniqueName;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "role", nullable = false)
    @Enumerated(STRING)
    private PlayerRole role;

    @Column(name = "colour", nullable = false)
    @Enumerated(STRING)
    private PlayerColour colour;

    @Column(name = "balance", nullable = false)
    private Long balance = INITIAL_BALANCE;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    private PlayerStatus status = INITIAL_PLAYER_STATUS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return this.id != null && this.id.equals(player.id)
                && this.uniqueName != null && this.uniqueName.equals(player.uniqueName)
                && this.position != null && this.position.equals(player.position)
                && this.role != null && this.role == player.role
                && this.colour != null && this.colour == player.colour
                && this.balance != null && this.balance.equals(player.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueName, position, role, colour, balance);
    }
}
