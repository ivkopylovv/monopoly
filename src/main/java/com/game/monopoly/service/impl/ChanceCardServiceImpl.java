package com.game.monopoly.service.impl;

import com.game.monopoly.dao.ChanceCardDAO;
import com.game.monopoly.entity.ChanceCard;
import com.game.monopoly.service.ChanceCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChanceCardServiceImpl implements ChanceCardService {
    private final ChanceCardDAO chanceCardDAO;

    @Override
    public List<ChanceCard> getChanceCards() {
        return chanceCardDAO.findAll();
    }
}
