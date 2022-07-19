package com.game.monopoly.service.impl;

import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.dto.response.CardDetailDTO;
import com.game.monopoly.entity.CardState;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.entity.Session;
import com.game.monopoly.helper.FindHelper;
import com.game.monopoly.mapper.CardStateMapper;
import com.game.monopoly.service.CompanyCardService;
import com.game.monopoly.service.SessionCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyCardServiceImpl implements CompanyCardService {
    private final SessionCommonService sessionCommonService;
    private final CompanyCardDAO companyCardDAO;

    @Transactional(readOnly = true)
    @Override
    public List<CompanyCard> getCompanyCards() {
        return companyCardDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CardDetailDTO getDetailedCardInfo(String sessionId, Long cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        CardState cardState = FindHelper.findCardStateByCardId(session.getCardStates(), cardId);

        return CardStateMapper.cardStateTOCardDetailDTO(cardState);
    }
}
