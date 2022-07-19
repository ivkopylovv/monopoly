package com.game.monopoly.service.impl;

import com.game.monopoly.dao.NonTypeCardDAO;
import com.game.monopoly.entity.NonTypeCard;
import com.game.monopoly.service.NonTypeCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NonTypeCardServiceImpl implements NonTypeCardService {
    private final NonTypeCardDAO nonTypeCardDAO;

    @Transactional(readOnly = true)
    @Override
    public List<NonTypeCard> getNonTypeCards() {
        return nonTypeCardDAO.findAll();
    }
}
