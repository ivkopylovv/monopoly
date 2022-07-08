package com.game.monopoly.service.impl;

import com.game.monopoly.dao.PlayerDAO;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.Player;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.game.monopoly.constants.ErrorMessage.CARD_NOT_FOUND;
import static com.game.monopoly.constants.ErrorMessage.PLAYER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;



    @Override
    public void changePlayerBalance(String name, Long moneyDiff) {
        playerDAO.updatePlayerBalanceByName(moneyDiff, name);
    }

    @Override
    public void buyCard(String name, Long cardNumber) {
       /* Player player = playerDAO.findPlayerByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));
        CompanyCard card = officeCardDAO.findById(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));

        card.setCurrentFine(card.getFines().get(card.getLevel()).getValue());
        Long cardPrice = card.getPrice();

        player.getCards().add(card);
        playerDAO.updatePlayerBalanceByName(-cardPrice, name);*/
    }

    @Override
    public void payForCard(String name, Long cardNumber) {
       /* CompanyCard card = officeCardDAO.findById(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
        playerDAO.updatePlayerBalanceByName(-card.getCurrentFine(), name);

        Player receivingPlayer = playerDAO.findPlayerByCardsId(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));
        playerDAO.updatePlayerBalanceByName(card.getCurrentFine(), receivingPlayer.getName());*/
    }
}
