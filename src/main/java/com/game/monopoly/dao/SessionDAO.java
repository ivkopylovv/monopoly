package com.game.monopoly.dao;

import com.game.monopoly.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionDAO extends JpaRepository<Session, String> {

}
