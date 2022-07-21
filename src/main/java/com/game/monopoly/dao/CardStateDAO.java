package com.game.monopoly.dao;

import com.game.monopoly.entity.CardState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardStateDAO extends JpaRepository<CardState, Long> {
    List<CardState> findByCardIdIn(List<Long> cardIds);
}
