package com.game.monopoly.service.impl;

import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.PlayingFieldStateDTO;
import com.game.monopoly.dto.response.PlayingFieldStaticDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CommonCard;
import com.game.monopoly.entity.Player;
import com.game.monopoly.entity.Session;
import com.game.monopoly.mapper.PlayingFieldMapper;
import com.game.monopoly.service.CommonCardService;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionCommonService;
import com.game.monopoly.service.SessionHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_MOVE_STATUS;
import static com.game.monopoly.enums.PlayerRole.ADMIN;

@Service
@RequiredArgsConstructor
public class SessionHttpServiceImpl implements SessionHttpService {
    private final SessionDAO sessionDAO;
    private final CommonCardService commonCardService;
    private final SessionCommonService sessionCommonService;
    private final PlayerService playerService;

    @Transactional
    @Override
    public void saveSession(String sessionId, String playerName, String colour, List<CardState> cardStates) {
        playerService.savePlayer(sessionId, playerName, colour, ADMIN);
        Player player = playerService.getPlayer(sessionId, playerName);

        Session session = new Session()
                .setId(sessionId)
                .setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME)
                .setMoveStatus(INITIAL_MOVE_STATUS)
                .setCardStates(cardStates);
        session.getPlayers().add(player);
        sessionDAO.save(session);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayingFieldStateDTO getStatePlayingField(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);

        return PlayingFieldMapper.buildPlayingFieldState(session);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayingFieldStaticDTO getStaticPlayingField() {
        List<CommonCard> cards = commonCardService.getAllCards();

        return PlayingFieldMapper.buildPlayingFieldStatic(cards);
    }

    @Transactional
    @Override
    public void finishSession(String sessionId) {
        sessionDAO.deleteById(sessionId);
    }

}
