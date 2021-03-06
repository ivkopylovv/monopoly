package com.game.monopoly.dao;

import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.enums.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDAO extends JpaRepository<Session, String> {

    @Modifying(clearAutomatically = true)
    @Query("update Session s " +
            "set s.state = ?1 " +
            "where s.id = ?2")
    void updateSessionState(SessionState state, String id);

    @Modifying(clearAutomatically = true)
    @Query("update Session s " +
            "set s.state = ?1, s.currentPlayer = ?2, s.moveStatus = ?3 " +
            "where s.id = ?4")
    void updateSessionStateAndCurrentPlayerAndMoveStatus(
            SessionState state, String nextPlayer, MoveStatus status, String id);

    @Modifying(clearAutomatically = true)
    @Query("update Session s " +
            "set s.currentPlayer = ?1, s.moveStatus = ?2 " +
            "where s.id = ?3")
    void updateCurrentPlayerAndMoveStatus(String nextPlayer, MoveStatus status, String id);

    @Modifying(clearAutomatically = true)
    @Query("update Session s " +
            "set s.moveStatus = ?1 " +
            "where s.id = ?2")
    void updateMoveStatus(MoveStatus moveStatus, String id);

    boolean existsSessionById(String id);
}
