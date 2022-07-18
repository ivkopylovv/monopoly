package com.game.monopoly.service.impl;

import com.game.monopoly.dao.ChanceCardDAO;
import com.game.monopoly.entity.ChanceCard;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.helper.RandomHelper;
import com.game.monopoly.service.ChanceCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.game.monopoly.constants.ErrorMessage.CARD_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ChanceCardServiceImpl implements ChanceCardService {
    private final ChanceCardDAO chanceCardDAO;

    @Transactional(readOnly = true)
    @Override
    public ChanceCard getRandomChanceCard() {
        Long count = getChanceCardCount();
        Long randomCardId = RandomHelper.getRandomCardId(count);

        return chanceCardDAO.findById(randomCardId).
                orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public Long getChanceCardCount() {
        return chanceCardDAO.count();
    }

}
