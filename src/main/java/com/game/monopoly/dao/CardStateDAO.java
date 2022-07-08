package com.game.monopoly.dao;

import com.game.monopoly.entity.CardState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardStateDAO extends JpaRepository<CardState, Long> {
}
