package com.game.monopoly.entity;

import com.game.monopoly.enums.SessionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private SessionState state;

    @ManyToMany(fetch = LAZY)
    @JoinColumn(name = "players", nullable = false)
    private List<Player> players = new ArrayList<>();
}
