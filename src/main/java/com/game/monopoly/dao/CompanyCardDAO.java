package com.game.monopoly.dao;

import com.game.monopoly.entity.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyCardDAO extends JpaRepository<CompanyCard, Long> {
    //List<CompanyCard> findByIdSessionId(String sessionId);
}
