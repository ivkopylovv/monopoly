package com.game.monopoly.service.impl;

import com.game.monopoly.dao.MessageDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.Message;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.MoveStatus;
import com.game.monopoly.helper.MessageHelper;
import com.game.monopoly.helper.PlayerPositionHelper;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.helper.SortHelper;
import com.game.monopoly.mapper.RoleDicesMapper;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionCommonService;
import com.game.monopoly.service.SessionProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.game.monopoly.constants.PlayingFieldParam.MAX_BORDER;
import static com.game.monopoly.constants.PlayingFieldParam.MIN_BORDER;
import static com.game.monopoly.enums.MoveStatus.MIDDLE;
import static com.game.monopoly.enums.MoveStatus.START;
import static com.game.monopoly.enums.PlayerRole.USER;
import static com.game.monopoly.enums.SessionState.IN_PROGRESS;

@Service
@RequiredArgsConstructor
public class SessionProcessServiceImpl implements SessionProcessService {
    private final MessageDAO messageDAO;
    private final SessionDAO sessionDAO;
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

        int firstRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int secondRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int newPosition = PlayerPositionHelper.getNewPosition(player.getPosition(), firstRoll, secondRoll);
        List<Integer> digits = List.of(firstRoll, secondRoll);

        playerService.updatePlayerPosition(newPosition, sessionId, playerName);

        sessionDAO.updateMoveStatus(MIDDLE, sessionId);
        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageHelper.createRollDicesMessage(playerName, digits);
        messageDAO.save(message);
        session.getMessages().add(message);

        return RoleDicesMapper.rollResultTODTO(digits, playerName, newPosition);
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
        Player nextPlayer = null;

        for (int i = 0; i < count; i++) {
            if (previousPLayer.equals(players.get(i).getUniqueName().getName())) {
                nextPlayer = i == count - 1 ? players.get(0) : players.get(i + 1);
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
}
