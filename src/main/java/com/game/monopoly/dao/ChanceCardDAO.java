package com.game.monopoly.dao;

import com.game.monopoly.entity.ChanceCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChanceCardDAO extends JpaRepository<ChanceCard, Long> {
}
