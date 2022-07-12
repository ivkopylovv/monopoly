package com.game.monopoly.service.impl;

import com.game.monopoly.dao.CompanyCardDAO;
import com.game.monopoly.entity.CompanyCard;
import com.game.monopoly.service.CompanyCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyCardServiceImpl implements CompanyCardService {
    private final CompanyCardDAO companyCardDAO;

    @Override
    public List<CompanyCard> getCompanyCards() {
        return companyCardDAO.findAll();
    }
}
