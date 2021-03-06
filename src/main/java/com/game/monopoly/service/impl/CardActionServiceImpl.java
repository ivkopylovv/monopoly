package com.game.monopoly.service.impl;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.dao.MessageDAO;
import com.game.monopoly.dao.SessionDAO;
import com.game.monopoly.dto.response.CardStatePlayerBalanceDTO;
import com.game.monopoly.dto.response.PayForCardDTO;
import com.game.monopoly.entity.*;
import com.game.monopoly.helper.FindHelper;
import com.game.monopoly.helper.MessageHelper;
import com.game.monopoly.mapper.CardActionMapper;
import com.game.monopoly.mapper.PlayerMapper;
import com.game.monopoly.service.CardActionService;
import com.game.monopoly.service.PlayerService;
import com.game.monopoly.service.SessionCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.game.monopoly.constants.InitialGameValue.INITIAL_CARD_FINE;
import static com.game.monopoly.constants.InitialGameValue.INITIAL_CARD_OWNER_NAME;
import static com.game.monopoly.enums.MoveStatus.END;
import static com.game.monopoly.enums.MoveStatus.START;

@Service
@RequiredArgsConstructor
public class CardActionServiceImpl implements CardActionService {
    private final SessionDAO sessionDAO;
    private final MessageDAO messageDAO;
    private final CardStateDAO cardStateDAO;
    private final SessionCommonService sessionCommonService;
    private final PlayerService playerService;

    @Transactional
    @Override
    public CardStatePlayerBalanceDTO buyCard(String sessionId, String playerName, Long cardId) {
        sessionDAO.updateMoveStatus(END, sessionId);
        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageHelper.createBuyCardMessage(playerName);
        messageDAO.save(message);
        session.getMessages().add(message);

        CardState cardState = FindHelper.findCardStateByCardId(session.getCardStates(), cardId);
        CompanyCard card = cardState.getCard();
        Player player = playerService.getPlayer(sessionId, playerName);

        Integer level = cardState.getLevel();
        Long fine = card.getFines().get(level).getValue();
        Long newBalance = player.getBalance() - card.getPrice();

        cardStateDAO.saveAndFlush(cardState
                .setCurrentFine(fine)
                .setOwnerName(playerName));
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        return CardActionMapper.cardActionTODTO(playerName, newBalance, cardState);
    }

    @Transactional
    @Override
    public CardStatePlayerBalanceDTO improveCard(String sessionId, String playerName, Long cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageHelper.createImproveCardMessage(playerName);
        messageDAO.save(message);
        session.getMessages().add(message);

        CardState cardState = FindHelper.findCardStateByCardId(session.getCardStates(), cardId);
        CompanyCard card = cardState.getCard();
        Player player = playerService.getPlayer(sessionId, playerName);

        Integer level = cardState.getLevel() + 1;
        Long fine = card.getFines().get(level).getValue();
        Long newBalance = player.getBalance() - card.getStarPrice();

        cardStateDAO.saveAndFlush(cardState
                .setCurrentFine(fine)
                .setLevel(level));
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        return CardActionMapper.cardActionTODTO(playerName, newBalance, cardState);
    }

    @Transactional
    @Override
    public CardStatePlayerBalanceDTO sellCard(String sessionId, String playerName, Long cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        Message message = null;

        CardState cardState = FindHelper.findCardStateByCardId(session.getCardStates(), cardId);
        CompanyCard card = cardState.getCard();
        Player player = playerService.getPlayer(sessionId, playerName);

        Long newBalance = player.getBalance();
        Integer level = cardState.getLevel();

        if (level > 0) {
            newBalance += card.getStarPrice();
            level -= 1;
            cardState
                    .setCurrentFine(card.getFines().get(level).getValue())
                    .setLevel(level);
            message = MessageHelper.createSellCardMessage(playerName);
        } else {
            newBalance += card.getSalePrice();
            cardState
                    .setOwnerName(INITIAL_CARD_OWNER_NAME)
                    .setCurrentFine(INITIAL_CARD_FINE);
            message = MessageHelper.createLowerCardLevelMessage(playerName);
        }

        messageDAO.save(message);
        session.getMessages().add(message);

        cardStateDAO.saveAndFlush(cardState);
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        return CardActionMapper.cardActionTODTO(playerName, newBalance, cardState);
    }

    @Transactional
    @Override
    public PayForCardDTO payForCard(String sessionId, String buyerName, Long cardId) {
        sessionDAO.updateMoveStatus(START, sessionId);
        Session session = sessionCommonService.getSession(sessionId);
        Message newMessage = MessageHelper.createPayForCardMessage(buyerName);
        messageDAO.save(newMessage);
        session.getMessages().add(newMessage);

        CardState cardState = FindHelper.findCardStateByCardId(session.getCardStates(), cardId);
        String ownerName = cardState.getOwnerName();
        Long fine = cardState.getCurrentFine();

        Player buyer = playerService.getPlayer(sessionId, buyerName);
        Player owner = playerService.getPlayer(sessionId, ownerName);
        Long buyerBalance = buyer.getBalance() - fine;
        Long ownerBalance = owner.getBalance() + fine;

        playerService.updatePlayerBalance(buyerBalance, sessionId, buyerName);
        playerService.updatePlayerBalance(ownerBalance, sessionId, ownerName);

        return PlayerMapper.playerBalancesToDTO(buyerName, buyerBalance, ownerName, ownerBalance);
    }
}
