package com.game.monopoly.dao;

import com.game.monopoly.entity.CardState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardStateDAO extends JpaRepository<CardState, Long> {
}
