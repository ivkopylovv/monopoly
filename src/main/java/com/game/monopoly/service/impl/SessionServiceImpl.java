package com.game.monopoly.service.impl;

import com.game.monopoly.constants.DiceBorderConstant;
import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.enums.SessionState;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.randomizer.StepsCountRandomizer;
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
    private final PlayerDAO playerDAO;
    private final CompanyCardDAO companyCardDAO;

    public void getPlayingField(String sessionId) {
        sessionDAO.updateSessionState(SessionState.IN_PROGRESS, sessionId);
        Session session = sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
        //List<CompanyCard> companyCards = companyCardDAO.findByIdSessionId(sessionId);

        PlayingFieldDTO playingFieldDTO = new PlayingFieldDTO();
        playingFieldDTO.setPlayers(session.getPlayers());
        playingFieldDTO.setState(String.valueOf(session.getState()));
    }


    @Override
    public void createSession(String sessionId, String playerName) {
        Player player = new Player()
                .setName(playerName);
        Session session = new Session()
                .setId(sessionId)
                .setState(NEW);
        session.getPlayers().add(player);
        playerDAO.save(player);
        sessionDAO.save(session);;
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
