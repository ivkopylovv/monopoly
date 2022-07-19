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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sphere", nullable = false)
    private String sphere;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "star_price", nullable = false)
    private Long starPrice;

    @Column(name = "sale_price", nullable = false)
    private Long salePrice;

    @Column(name = "collection_number", nullable = false)
    private Integer collectionNumber;

    @ManyToMany(fetch = LAZY)
    @JoinColumn(name = "level_fines", nullable = false)
    private List<LevelFine> fines = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "common_card_id", nullable = false)
    private CommonCard commonCard;
}
