package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
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
}
