package com.game.monopoly.service.impl;

import com.game.monopoly.dao.*;
import com.game.monopoly.dto.response.PlayingFieldDTO;
import com.game.monopoly.dto.response.RollDiceResultDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.mapper.CardMapper;
import com.game.monopoly.mapper.RoleDicesMapper;
import com.game.monopoly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.game.monopoly.constants.ErrorMessage.PLAYER_NOT_FOUND;
import static com.game.monopoly.constants.ErrorMessage.SESSION_NOT_FOUND;
import static com.game.monopoly.constants.PlayingFieldParam.*;
import static com.game.monopoly.enums.SessionState.NEW;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionDAO;
    private final PlayerDAO playerDAO;
    private final CompanyCardDAO companyCardDAO;
    private final ChanceCardDAO chanceCardDAO;
    private final NonTypeCardDAO nonTypeCardDAO;
    private final CardStateDAO cardStateDAO;

    @Override
    public PlayingFieldDTO getPlayingField(String sessionId) {
        Session session = sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
        List<CompanyCard> companyCards = companyCardDAO.findAll();

        List<ChanceCard> chanceCards = chanceCardDAO.findAll();
        List<NonTypeCard> nonTypeCards = nonTypeCardDAO.findAll();

        PlayingFieldDTO playingFieldDTO = new PlayingFieldDTO()
                .setPlayers(session.getPlayers())
                .setState(String.valueOf(session.getState()))
                .setCards(CardMapper.allCardsToCommonsList(companyCards, chanceCards, nonTypeCards))
                .setCardStates(CardMapper.cardStatesEntitiesToDTOList(session.getCardStates()));

        return playingFieldDTO;
    }


    @Override
    public void createSession(String sessionId, String playerName) {
        Player player = new Player()
                .setName(playerName)
                .setPosition(0);
        Session session = new Session()
                .setId(sessionId)
                .setState(NEW);

        playerDAO.save(player);
        session.getPlayers().add(player);

        List<CardState> cardStates = new ArrayList<>();
        List<CompanyCard> companyCards = companyCardDAO.findAll();

        companyCards
                .stream()
                .map(companyCard -> new CardState()
                        .setCard(companyCard)
                        .setCurrentFine(null)
                        .setLevel(0)
                        .setOwnerName(null))
                .forEach(cardState -> {
                    cardStateDAO.save(cardState);
                    cardStates.add(cardState);
                });

        session.getCardStates().addAll(cardStates);
        sessionDAO.save(session);
    }

    @Override
    public void addPlayer(String sessionId, String playerName) {
        Player player = new Player()
                .setName(playerName)
                .setPosition(0);
        Session session = sessionDAO.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));

        playerDAO.save(player);
        session.getPlayers().add(player);
    }

    @Override
    public RollDiceResultDTO rollDices(String sessionId, String playerName) {
        Player player = playerDAO.findPlayerByName(playerName)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));

        int firstRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int secondRoll = RandomHelper.getRandomDiceValue(MIN_BORDER, MAX_BORDER);
        int potentialPos = player.getPosition() + firstRoll + secondRoll;
        int newPos = potentialPos < FIELD_SIZE ? potentialPos : potentialPos - FIELD_SIZE;

        playerDAO.updatePlayerPositionByName(newPos, playerName);

        return RoleDicesMapper.rollResultTODTO(List.of(firstRoll, secondRoll), playerName, newPos);
    }
}
