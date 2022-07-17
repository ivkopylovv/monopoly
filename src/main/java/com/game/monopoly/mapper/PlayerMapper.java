package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.PlayerDTO;
import com.game.monopoly.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static List<PlayerDTO> entitiesToDTOList(List<Player> players) {
        return players
                .stream()
                .map(PlayerMapper::entityToPLayerDTO)
                .collect(Collectors.toList());
    }

    public static PlayerDTO entityToPLayerDTO(Player player) {
        return new PlayerDTO()
                .setId(player.getId())
                .setName(player.getUniqueName().getName())
                .setColour(player.getColour().toString())
                .setPosition(player.getPosition())
                .setRole(player.getRole().toString())
                .setBalance(player.getBalance());
    }
}
