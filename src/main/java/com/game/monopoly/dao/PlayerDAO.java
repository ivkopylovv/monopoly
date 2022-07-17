package com.game.monopoly.dao;

import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.embedded.PlayerUniqueName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {
    Optional<Player> findPlayerByUniqueName(PlayerUniqueName name);

    @Modifying(clearAutomatically = true)
    @Query("update Player p " +
            "set p.balance = ?1 " +
            "where p.uniqueName = ?2")
    void updatePlayerBalanceByName(Long newBalance, PlayerUniqueName name);

    @Modifying(clearAutomatically = true)
    @Query("update Player p " +
            "set p.position = ?1 " +
            "where p.uniqueName = ?2")
    void updatePlayerPositionByName(Integer position, PlayerUniqueName name);
}
