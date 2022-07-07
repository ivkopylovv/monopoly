package com.game.monopoly.service.impl;

import com.game.monopoly.constants.DiceBorderConstant;
import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.randomizer.StepsCountRandomizer;
import com.game.monopoly.randomizer.StringIdRandomizer;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.game.monopoly.constants.ErrorMessage.SESSION_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionDAO sessionDAO;
    private final PlayerDAO playerDAO;

    @Override
    public String createSession(String playerName) {
        String sessionId = StringIdRandomizer.randomizeStringId();
        Player player = new Player().setName(playerName);
        Session session = new Session().setId(sessionId);
        session.getPlayers().add(player);
        playerDAO.save(player);
        sessionDAO.save(session);
        return sessionId;
    }

    @Override
    public void addPlayer(String sessionId, String playerName) {
        Player player = new Player().setName(playerName);
        playerDAO.save(player);
        Session session = sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
        session.getPlayers().add(player);
    }

    @Override
    public List<Long> randomSteps(String sessionId, String playerName) {
        Long firstRollResult = StepsCountRandomizer.randomizeStepsCount(DiceBorderConstant.MIN_BORDER,
                DiceBorderConstant.MAX_BORDER);
        Long secondRollResult = StepsCountRandomizer.randomizeStepsCount(DiceBorderConstant.MIN_BORDER,
                DiceBorderConstant.MAX_BORDER);
        return List.of(firstRollResult, secondRollResult);
    }
}
