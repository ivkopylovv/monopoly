package com.game.monopoly.service.impl;

import com.game.monopoly.dao.MessageDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.PlayerStatusDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Message;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.helper.MessageHelper;
import com.game.monopoly.helper.PlayerPositionHelper;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.helper.SortHelper;
import com.game.monopoly.mapper.PlayerMapper;
import com.game.monopoly.mapper.RollDicesMapper;
import com.game.monopoly.service.ChatService;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionCommonService;
import com.game.monopoly.service.SessionProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.List;

import static com.game.monopoly.constants.EventParam.START_BONUS;
import static com.game.monopoly.constants.ResultMessage.SURRENDER;
import static com.game.monopoly.enums.MoveStatus.MIDDLE;
import static com.game.monopoly.enums.MoveStatus.START;
import static com.game.monopoly.enums.PlayerRole.USER;
import static com.game.monopoly.enums.PlayerStatus.LOST;
import static com.game.monopoly.enums.PlayerStatus.PLAYING;
import static com.game.monopoly.enums.SessionState.IN_PROGRESS;

@Service
@RequiredArgsConstructor
public class SessionProcessServiceImpl implements SessionProcessService {
    private final MessageDAO messageDAO;
    private final SessionDAO sessionDAO;
    private final ChatService chatService;
    private final SessionCommonService sessionCommonService;
    private final PlayerService playerService;

    @Transactional
    @Override
    public Player addPlayerToSession(String sessionId, String playerName, String colour) {
        Session session = sessionCommonService.getSession(sessionId);
        playerService.savePlayer(sessionId, playerName, colour, USER);
        Player player = playerService.getPlayer(sessionId, playerName);

        Message message = MessageHelper.createAddPlayerMessage(playerName);
        messageDAO.save(message);
        session.getPlayers().add(player);
        session.getMessages().add(message);

        return player;
    }

    @Transactional
    @Override
    public RollDiceResultDTO rollDices(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);

        int firstRoll = RandomHelper.getRandomDiceValue();
        int secondRoll = RandomHelper.getRandomDiceValue();
        int position = player.getPosition();
        int newPosition = PlayerPositionHelper.getNewPosition(position, firstRoll, secondRoll);
        List<Integer> digits = List.of(firstRoll, secondRoll);
        Message updMessage = null;
        Long balance = null;

        playerService.updatePlayerPosition(newPosition, sessionId, playerName);

        if (position > newPosition) {
            balance = player.getBalance() + START_BONUS;
            playerService.updatePlayerBalance(balance, sessionId, playerName);
            updMessage = MessageHelper.createStartBonusMessage(playerName);
        }

        sessionDAO.updateMoveStatus(MIDDLE, sessionId);
        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageHelper.createRollDicesMessage(playerName, digits);
        messageDAO.save(message);
        session.getMessages().add(message);

        if (updMessage != null) {
            messageDAO.save(updMessage);
            session.getMessages().add(updMessage);
        }

        return RollDicesMapper.rollResultTODTO(digits, playerName, newPosition, balance);
    }

    @Transactional
    @Override
    public void startGame(String sessionId, String nextPlayer) {
        sessionDAO.updateSessionStateAndCurrentPlayerAndMoveStatus(IN_PROGRESS, nextPlayer, START, sessionId);

        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageHelper.createStartGameMessage();
        messageDAO.save(message);
        session.getMessages().add(message);
    }

    @Transactional
    @Override
    public String getNextPlayer(String sessionId, String previousPLayer) {
        List<Player> players = SortHelper.getSortedPlayers(
                sessionCommonService.getSession(sessionId).getPlayers());
        int count = players.size();

        ArrayDeque<Player> deque = new ArrayDeque<>();
        int idx = 0;

        for (int i = 0; i < count; i++) {
            Player player = players.get(i);

            if (previousPLayer.equals(player.getUniqueName().getName())) {
                idx = i;
                break;
            }

            deque.addLast(player);
        }

        for (int i = count - 1; i > idx; i--) {
            deque.addFirst(players.get(i));
        }

        Player nextPlayer = null;

        while (!deque.isEmpty()) {
            Player player = deque.pop();

            if (player.getStatus() == PLAYING) {
                nextPlayer = player;
                break;
            }
        }

        String nextPlayerName = nextPlayer.getUniqueName().getName();
        sessionDAO.updateCurrentPlayerAndMoveStatus(nextPlayerName, START, sessionId);

        return nextPlayerName;
    }

    @Override
    public MoveStatus getCurrentMoveStatus(String sessionId) {
        return sessionCommonService.getSession(sessionId).getMoveStatus();
    }

    @Override
    public PlayerStatusDTO getSurrenderPlayer(String sessionId, String playerName) {
        playerService.updatePlayerStatus(LOST, sessionId, playerName);
        chatService.addCommonMessageToChatHistory(sessionId, playerName, SURRENDER);

        return PlayerMapper.playerStatusToDTO(playerName, LOST);
    }
}
