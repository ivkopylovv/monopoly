package com.game.monopoly.dao;

import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDAO extends JpaRepository<Session, String> {

    @Modifying(clearAutomatically = true)
    @Query("update Session s set s.state = ?1 where s.id = ?2")
    void updateSessionState(SessionState state, String id);
}
