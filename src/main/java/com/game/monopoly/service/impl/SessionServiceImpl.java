package com.game.monopoly.service.impl;

import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.game.monopoly.constants.ErrorMessage.SESSION_NOT_FOUND;
import static com.game.monopoly.enums.SessionState.NEW;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionDAO;

    @Override
    public Session getSession(String sessionId) {
        return sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
    }

    @Override
    public void saveSession(String sessionId, Player player, List<CardState> cardStates) {
        Session session = new Session()
                .setId(sessionId)
                .setState(NEW)
                .setCardStates(cardStates);
        session.getPlayers().add(player);
        sessionDAO.save(session);
    }

    @Override
    public void addPlayerToSession(Session session, Player player) {
        session.getPlayers().add(player);
    }

}
