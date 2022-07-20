package com.game.monopoly.entity;

import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.enums.SessionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.game.monopoly.enums.SessionState.NEW;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "session")
public class Session {
    @Id
    private String id;

    @Column(name = "state", nullable = false)
    @Enumerated(STRING)
    private SessionState state = NEW;

    @Column(name = "current_player")
    private String currentPlayer;

    @Column(name = "move_status")
    @Enumerated(STRING)
    private MoveStatus moveStatus;

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "players")
    private List<Player> players = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "card_states")
    private List<CardState> cardStates = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "messages")
    private List<Message> messages = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return this.id != null && this.id.equals(session.id)
                && this.state != null && this.state == session.state
                && Objects.equals(this.currentPlayer, session.currentPlayer)
                && this.moveStatus == session.moveStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, currentPlayer, moveStatus, players, cardStates, messages);
    }
}
