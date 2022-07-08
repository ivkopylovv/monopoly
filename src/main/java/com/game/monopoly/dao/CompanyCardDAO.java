package com.game.monopoly.dao;

import com.game.monopoly.entity.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCardDAO extends JpaRepository<CompanyCard, Long> {
}
