package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "balance", nullable = false)
    private Long balance = 15000L;

    @ManyToMany(fetch = EAGER)
    @JoinColumn(name = "cards", nullable = false)
    private List<OfficeCard> cards = new ArrayList<>();
}