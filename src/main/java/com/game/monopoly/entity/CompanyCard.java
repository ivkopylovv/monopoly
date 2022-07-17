package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company_card")
public class CompanyCard {
    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "star_price", nullable = false)
    private Long starPrice;

    @Column(name = "collection_number", nullable = false)
    private Integer collectionNumber;

    @ManyToMany(fetch = LAZY)
    @JoinColumn(name = "level_fines")
    private List<LevelFine> fines = new ArrayList<>();
}
