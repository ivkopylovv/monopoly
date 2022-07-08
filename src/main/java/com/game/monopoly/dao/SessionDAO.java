package com.game.monopoly.dao;

import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionDAO extends JpaRepository<Session, String> {
    @Modifying
    @Query("update Session s set s.state = ?1 where s.id = ?2")
    void updateSessionState(SessionState state, String id);

}
