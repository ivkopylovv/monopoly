package com.game.monopoly.controller;

import com.game.monopoly.dto.request.*;
import com.game.monopoly.dto.response.*;
import com.game.monopoly.entity.Player;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.helper.MapHelper;
import com.game.monopoly.mapper.MessageContentMapper;
import com.game.monopoly.mapper.PlayerMapper;
import com.game.monopoly.mapper.RollDicesMapper;
import com.game.monopoly.service.ChatService;
import com.game.monopoly.service.SessionWebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

import static com.game.monopoly.constants.ResultMessage.*;
import static com.game.monopoly.enums.SessionState.IN_PROGRESS;

@Controller
@RequiredArgsConstructor
public class SessionWebSocketController {
    private final SessionWebSocketService sessionWebSocketService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/sessions/add-player")
    public void addPlayer(InitializeSessionDTO dto) {
        Player player = sessionWebSocketService.addPlayerToSession(dto.getSessionId(), dto.getPlayerName(), dto.getColour());
        PlayerDTO playerDTO = PlayerMapper.entityToPLayerDTO(player);
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), NEW_PLAYER);

        simpMessagingTemplate.convertAndSend("/topic/add-player/" + dto.getSessionId(), playerDTO);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/roll-dice")
    public void getNewPlayerPosition(SessionPlayerNameDTO dto) {
        RollDiceResultDTO rollDiceResult = sessionWebSocketService.rollDices(dto.getSessionId(), dto.getPlayerName());
        Long balance = rollDiceResult.getPlayerBalance().getBalance();

        if (balance != null) {
            ResultMessageDTO startMessage = new ResultMessageDTO(dto.getPlayerName(), START_BONUS_PASS);
            simpMessagingTemplate.convertAndSend(
                    "/topic/change-balance/" + dto.getSessionId(),
                    rollDiceResult.getPlayerBalance());
            simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), startMessage);
        }

        ResultMessageDTO resultMessage = new ResultMessageDTO(
                dto.getPlayerName(),
                MessageContentMapper.rollDiceToMessageContent(rollDiceResult.getDigits())
        );
        MoveResultDTO result = RollDicesMapper.rollResultToMoveDTO(rollDiceResult);

        simpMessagingTemplate.convertAndSend("/topic/roll-dice/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/start-game")
    public void startGame(GameEventDTO dto) {
        sessionWebSocketService.startGame(dto.getSessionId(), dto.getPlayerName());

        StartGameResultDTO result = new StartGameResultDTO(IN_PROGRESS.toString(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(null, GAME_WAS_STARTED);

        simpMessagingTemplate.convertAndSend("/topic/start-game/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/move-transition")
    public String moveTransition(GameEventDTO dto) {
        String currentPlayer = sessionWebSocketService.getNextPlayer(dto.getSessionId(), dto.getPlayerName());
        CurrentPlayerDTO result = new CurrentPlayerDTO(currentPlayer);

        simpMessagingTemplate.convertAndSend("/topic/move-transition/" + dto.getSessionId(), result);

        return currentPlayer;
    }

    @MessageMapping(value = "/sessions/move-status")
    public void getCurrentMoveStatus(SessionIdDTO dto) {
        MoveStatus status = sessionWebSocketService.getCurrentMoveStatus(dto.getSessionId());
        MoveStatusDTO result = new MoveStatusDTO(String.valueOf(status));

        simpMessagingTemplate.convertAndSend("/topic/move-status/" + dto.getSessionId(), result);
    }

    @MessageMapping(value = "/sessions/surrender")
    public void surrenderTheGame(GameEventDTO dto) {
        SurrenderPlayerDTO result = sessionWebSocketService.getSurrenderPlayer(dto.getSessionId(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), SURRENDER);

        simpMessagingTemplate.convertAndSend("/topic/surrender/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/sessions/send-offer")
    public void sendContract(OfferDTO offer) {
        String sessionId = offer.getSessionId();
        String creatorName = offer.getCreator().getPlayerName();
        String receiverName = offer.getReceiver().getPlayerName();
        String message = MessageContentMapper.sendOfferToMessageContent(receiverName);
        ResultMessageDTO resultMessage = new ResultMessageDTO(creatorName, message);

        chatService.addCommonMessageToChatHistory(sessionId, null, message);

        simpMessagingTemplate.convertAndSend("/topic/send-contract/" + sessionId + "/" + receiverName, offer);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }

    @MessageMapping(value = "/sessions/reject-offer")
    public void rejectOffer(GameEventDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, REJECT_OFFER);

        chatService.addCommonMessageToChatHistory(sessionId, playerName, REJECT_OFFER);

        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }

    @MessageMapping(value = "/sessions/accept-offer")
    public void acceptOffer(OfferDTO offer) {
        String sessionId = offer.getSessionId();
        PlayerOfferDTO creator = offer.getCreator();
        PlayerOfferDTO receiver = offer.getReceiver();

        ChangeBalanceCardStateDTO creatorDTO = sessionWebSocketService
                .acceptOffer(
                        sessionId,
                        creator.getPlayerName(),
                        receiver.getPlayerName(),
                        receiver.getMoney(),
                        creator.getCards()
                );
        ChangeBalanceCardStateDTO receiverDTO = sessionWebSocketService
                .acceptOffer(
                        sessionId,
                        receiver.getPlayerName(),
                        creator.getPlayerName(),
                        creator.getMoney(),
                        receiver.getCards()
                );
        chatService.addCommonMessageToChatHistory(sessionId, receiver.getPlayerName(), ACCEPT_OFFER);

        Map<Long, CardStateDTO> cardStates = MapHelper
                .mergeTwoMaps(creatorDTO.getCardStates(), receiverDTO.getCardStates());
        OfferAcceptDTO result = new OfferAcceptDTO(creatorDTO.getPlayer(), receiverDTO.getPlayer(), cardStates);
        ResultMessageDTO resultMessage = new ResultMessageDTO(receiver.getPlayerName(), ACCEPT_OFFER);

        simpMessagingTemplate.convertAndSend("/topic/accept-offer/" + sessionId, result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }
}
