package com.game.monopoly.controller;

import com.game.monopoly.dto.request.CommonMessageDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;
import com.game.monopoly.dto.response.SuccessMessageDTO;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.game.monopoly.constants.ResultMessage.COMMON_MESSAGE_WAS_SAVED;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SessionService sessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/chat/common")
    public ResponseEntity<SuccessMessageDTO> saveCommonMessage(CommonMessageDTO dto) {
        sessionService.addCommonMessageToChatHistory(dto.getSessionId(), dto.getSender(), dto.getMessage());
        simpMessagingTemplate.convertAndSend(
                "/topic/chat/" + dto.getSessionId(),
                new ResultMessageDTO(dto.getSender(), dto.getMessage())
        );

        return ResponseEntity.ok().body(new SuccessMessageDTO(COMMON_MESSAGE_WAS_SAVED));
    }

    /*@MessageMapping(value = "/chat/single")
    public ResponseEntity<SuccessMessageDTO> saveCommonMessage(SingleMessageDTO dto) {
        chatService.saveSingleMessage(dto.getSessionId(), dto.getSender(), dto.getReceiver(), dto.getMessage());
        simpMessagingTemplate.convertAndSend(
                "/topic/chat/" + dto.getSessionId() + dto.getReceiver(),
                new ResultMessageDTO(dto.getMessage()));

        return ResponseEntity.ok().body(new SuccessMessageDTO(SINGLE_MESSAGE_WAS_SAVED));
    }*/
}
