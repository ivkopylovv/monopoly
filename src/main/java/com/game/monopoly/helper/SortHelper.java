package com.game.monopoly.helper;

import com.game.monopoly.entity.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortHelper {

    public static List<Player> getSortedPlayers(List<Player> players) {
        return players
                .stream()
                .sorted(Comparator.comparing(Player::getId))
                .collect(Collectors.toList());
    }
}
