package com.game.monopoly.dao;

import com.game.monopoly.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends JpaRepository<Message, Long> {
}
