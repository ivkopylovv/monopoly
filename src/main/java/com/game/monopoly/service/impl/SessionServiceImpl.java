package com.game.monopoly.service.impl;

import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.exception.ResourceAlreadyExistsException;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.helper.PlayerPositionHelper;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.mapper.RoleDicesMapper;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.game.monopoly.constants.ErrorMessage.*;
import static com.game.monopoly.constants.PlayingFieldParam.MAX_BORDER;
import static com.game.monopoly.constants.PlayingFieldParam.MIN_BORDER;
import static com.game.monopoly.enums.PlayerRole.ADMIN;
import static com.game.monopoly.enums.PlayerRole.USER;
import static com.game.monopoly.enums.SessionState.IN_PROGRESS;
import static com.game.monopoly.enums.SessionState.NEW;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionDAO;
    private final PlayerService playerService;

    @Transactional(readOnly = true)
    @Override
    public Session getSession(String sessionId) {
        return sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
    }

    @Transactional
    @Override
    public void saveSession(String sessionId, String playerName, String colour, List<CardState> cardStates) {
        sessionDAO.findById(sessionId)
                .ifPresent((session -> {
                    throw new ResourceAlreadyExistsException(SESSION_ALREADY_EXISTS);
                }));
        playerService.savePlayer(playerName, colour, ADMIN);
        Player player = playerService.getPlayer(playerName);
        Session session = new Session()
                .setId(sessionId)
                .setState(NEW)
                .setCardStates(cardStates);
        session.getPlayers().add(player);
        sessionDAO.save(session);
    }

    @Transactional
    @Override
    public Player addPlayerToSession(String sessionId, String playerName, String colour) {
        playerService.savePlayer(playerName, colour, USER);
        Session session = getSession(sessionId);
        Player player = playerService.getPlayer(playerName);
        session.getPlayers().add(player);

        return player;
    }

    @Transactional
    @Override
    public RollDiceResultDTO rollDices(String playerName) {
        Player player = playerService.getPlayer(playerName);

        int firstRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int secondRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int newPosition = PlayerPositionHelper.getNewPosition(player.getPosition(), firstRoll, secondRoll);

        playerService.updatePlayerPosition(newPosition, playerName);

        return RoleDicesMapper.rollResultTODTO(List.of(firstRoll, secondRoll), playerName, newPosition);
    }

    @Transactional
    @Override
    public void startGame(String sessionId) {
        sessionDAO.updateSessionState(IN_PROGRESS, sessionId);
    }

}
