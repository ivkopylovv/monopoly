package com.game.monopoly.controller;

import com.game.monopoly.dto.request.CommonMessageDTO;
import com.game.monopoly.dto.request.SingleMessageDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.game.monopoly.constants.ResultMessage.COMMON_MESSAGE_WAS_SAVED;
import static com.game.monopoly.constants.ResultMessage.SINGLE_MESSAGE_WAS_SAVED;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/chat/common")
    public ResponseEntity<SuccessMessageDTO> saveCommonMessage(CommonMessageDTO dto) {
        messageService.saveCommonMessage(dto.getSessionId(), dto.getSender(), dto.getMessage());
        simpMessagingTemplate.convertAndSend(
                "/topic/chat/" + dto.getSessionId(),
                new ResultMessageDTO(dto.getMessage()));

        return ResponseEntity.ok().body(new SuccessMessageDTO(COMMON_MESSAGE_WAS_SAVED));
    }

    @MessageMapping(value = "/chat/single")
    public ResponseEntity<SuccessMessageDTO> saveCommonMessage(SingleMessageDTO dto) {
        messageService.saveSingleMessage(dto.getSessionId(), dto.getSender(), dto.getReceiver(), dto.getMessage());
        simpMessagingTemplate.convertAndSend(
                "/topic/chat/" + dto.getSessionId() + dto.getReceiver(),
                new ResultMessageDTO(dto.getMessage()));

        return ResponseEntity.ok().body(new SuccessMessageDTO(SINGLE_MESSAGE_WAS_SAVED));
    }
}
