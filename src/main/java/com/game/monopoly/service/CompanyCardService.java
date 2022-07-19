package com.game.monopoly.service;

import com.game.monopoly.dto.response.CardDetailDTO;
import com.game.monopoly.entity.CompanyCard;

import java.util.List;

public interface CompanyCardService {
    List<CompanyCard> getCompanyCards();

    CardDetailDTO getDetailedCardInfo(String sessionId, Long cardId);

}
