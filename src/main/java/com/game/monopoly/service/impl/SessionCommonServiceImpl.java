package com.game.monopoly.service.impl;

import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.entity.Session;
import com.game.monopoly.exception.ResourceAlreadyExistsException;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.service.SessionCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.game.monopoly.constants.ErrorMessage.SESSION_ALREADY_EXISTS;
import static com.game.monopoly.constants.ErrorMessage.SESSION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SessionCommonServiceImpl implements SessionCommonService {
    private final SessionDAO sessionDAO;

    @Transactional(readOnly = true)
    @Override
    public Session getSession(String sessionId) {
        return sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public void checkSessionExists(String sessionId) {
        if (sessionDAO.existsSessionById(sessionId)) {
            throw new ResourceAlreadyExistsException(SESSION_ALREADY_EXISTS);
        }
    }
}
