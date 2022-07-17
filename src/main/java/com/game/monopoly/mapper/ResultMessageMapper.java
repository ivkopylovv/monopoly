package com.game.monopoly.mapper;

import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

public class ResultMessageMapper {

    public static List<ResultMessageDTO> entitiesToDTOList(List<Message> messages) {
        return messages
                .stream()
                .map(ResultMessageMapper::entityToMessageDTO)
                .collect(Collectors.toList());
    }

    private static ResultMessageDTO entityToMessageDTO(Message message) {
        return new ResultMessageDTO()
                .setPlayerName(message.getSender())
                .setMessage(message.getContent());
    }
}
