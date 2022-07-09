package com.game.monopoly.dao;

import com.game.monopoly.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {
    Optional<Player> findPlayerByName(String name);

    @Modifying
    @Query("update Player p " +
            "set p.balance = ?1 " +
            "where p.name = ?2")
    void updatePlayerBalanceByName(Long moneyDiff, String name);

    @Modifying
    @Query("update Player p " +
            "set p.position = ?1 " +
            "where p.name = ?2")
    void updatePlayerPositionByName(Integer position, String name);
}
