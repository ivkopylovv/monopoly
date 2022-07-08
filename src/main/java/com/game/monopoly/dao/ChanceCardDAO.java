package com.game.monopoly.dao;

import com.game.monopoly.entity.ChanceCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChanceCardDAO extends JpaRepository<ChanceCard, Long> {
}
