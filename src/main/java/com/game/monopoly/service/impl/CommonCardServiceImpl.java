package com.game.monopoly.service.impl;

import com.game.monopoly.dao.CommonCardDAO;
import com.game.monopoly.entity.CommonCard;
import com.game.monopoly.service.CommonCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonCardServiceImpl implements CommonCardService {
    private final CommonCardDAO commonCardDAO;

    @Transactional(readOnly = true)
    @Override
    public List<CommonCard> getAllCards() {
        return commonCardDAO.findAll();
    }
}
