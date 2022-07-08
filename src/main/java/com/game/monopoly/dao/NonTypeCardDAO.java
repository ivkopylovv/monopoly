package com.game.monopoly.dao;

import com.game.monopoly.entity.NonTypeCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonTypeCardDAO extends JpaRepository<NonTypeCard, Long> {
}
