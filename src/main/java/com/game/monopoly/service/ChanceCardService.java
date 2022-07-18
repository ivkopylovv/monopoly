package com.game.monopoly.service;

import com.game.monopoly.entity.ChanceCard;

public interface ChanceCardService {
    ChanceCard getRandomChanceCard();

    Long getChanceCardCount();

}
