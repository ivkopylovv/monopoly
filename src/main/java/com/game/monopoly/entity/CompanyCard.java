package com.game.monopoly.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyCard)) return false;
        CompanyCard that = (CompanyCard) o;
        return this.id != null && this.id.equals(that.id)
                && this.title != null && this.title.equals(that.title)
                && this.sphere != null && this.sphere.equals(that.sphere)
                && this.price != null && this.price.equals(that.price)
                && this.starPrice != null && this.starPrice.equals(that.starPrice)
                && this.salePrice != null && this.salePrice.equals(that.salePrice)
                && this.collectionNumber != null && this.collectionNumber.equals(that.collectionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sphere, price, starPrice, salePrice, collectionNumber, fines, commonCard);
    }
}
