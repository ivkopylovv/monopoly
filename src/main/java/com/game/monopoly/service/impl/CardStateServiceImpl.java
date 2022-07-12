package com.game.monopoly.service.impl;

import com.game.monopoly.dao.CardStateDAO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.CardStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.game.monopoly.constants.InitialGameValue.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CardStateServiceImpl implements CardStateService {
    private final CardStateDAO cardStateDAO;

    @Override
    public List<CardState> getNewCardStates(List<CompanyCard> companyCards) {
        return companyCards
                .stream()
                .map(companyCard -> new CardState()
                        .setCard(companyCard)
                        .setCurrentFine(INITIAL_CARD_FINE)
                        .setLevel(INITIAL_CARD_LEVEL)
                        .setOwnerName(INITIAL_CARD_OWNER_NAME))
                .collect(Collectors.toList());
    }

    @Override
    public void saveCardStates(List<CardState> cardStates) {
        cardStateDAO.saveAll(cardStates);
    }

}
