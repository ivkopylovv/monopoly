package com.game.monopoly.service;

import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;

import java.util.List;

public interface CardStateService {
    List<CardState> getNewCardStates(List<CompanyCard> companyCards);

    void saveCardStates(List<CardState> cardStates);
}
