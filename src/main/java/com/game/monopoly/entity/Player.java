package com.game.monopoly.entity;

import com.game.monopoly.constants.EconomicalConstant;
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

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "balance", nullable = false)
    private Long balance = EconomicalConstant.DEFAULT_BALANCE;
}
