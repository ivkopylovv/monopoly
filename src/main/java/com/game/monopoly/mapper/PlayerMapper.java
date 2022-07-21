package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.*;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.PlayerStatus;

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
                .setBalance(player.getBalance())
                .setStatus(player.getStatus());
    }

    public static PayForCardDTO playerBalancesToDTO(
            String buyer, Long buyerBalance, String owner, Long ownerBalance) {
        return new PayForCardDTO()
                .setBuyer(PlayerMapper.playerBalanceToDTO(buyer, buyerBalance))
                .setOwner(PlayerMapper.playerBalanceToDTO(owner, ownerBalance));
    }

    public static PlayerPositionDTO playerPositionToDTO(String playerName, int position) {
        return new PlayerPositionDTO(playerName, position);
    }

    public static PlayerBalanceDTO playerBalanceToDTO(String playerName, Long balance) {
        return new PlayerBalanceDTO(playerName, balance);
    }

    public static SurrenderPlayerDTO surrenderPlayerToDTO(String playerName, PlayerStatus status, List<CardState> cardStates) {
        return new SurrenderPlayerDTO()
                .setPlayer(PlayerMapper.playerStatusToDTO(playerName, status))
                .setCardStates(PlayingFieldMapper.cardStatesEntitiesToDTOList(cardStates));
    }

    public static PlayerStatusDTO playerStatusToDTO(String playerName, PlayerStatus status) {
        return new PlayerStatusDTO(playerName, String.valueOf(status));
    }
}
