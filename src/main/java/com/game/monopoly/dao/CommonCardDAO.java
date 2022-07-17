package com.game.monopoly.dao;

import com.game.monopoly.entity.CommonCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonCardDAO extends JpaRepository<CommonCard, Long> {
}
