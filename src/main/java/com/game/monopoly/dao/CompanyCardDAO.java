package com.game.monopoly.dao;

import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.compositekey.CompanyCardId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyCardDAO extends JpaRepository<CompanyCard, CompanyCardId> {
    List<CompanyCard> findByIdSessionId(String sessionId);
}
