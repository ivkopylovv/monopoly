package com.game.monopoly.entity;

import com.game.monopoly.entity.compositekey.CompanyCardId;
import com.game.monopoly.enums.CardType;
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
@Table(name = "company_card")
public class CompanyCard {
    @EmbeddedId
    private CompanyCardId id;

    @Column(name = "title")
    private String title;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "current_fine")
    private Long currentFine;

    @Column(name = "star_price", nullable = false)
    private Long starPrice;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "type", nullable = false)
    @Enumerated(value = STRING)
    private CardType type;

    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "collection_number", nullable = false)
    private Integer collectionNumber;

    @ManyToMany(fetch = LAZY)
    @JoinColumn(name = "level_fines")
    private List<LevelFine> fines = new ArrayList<>();
}
