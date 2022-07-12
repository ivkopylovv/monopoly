package com.game.monopoly.mapper;

import com.game.monopoly.dto.request.ChangeBalanceDTO;
import com.game.monopoly.dto.request.InitializeSessionDTO;
import com.game.monopoly.dto.request.PerformActionWithCardDTO;
import com.game.monopoly.dto.response.ResultMessageDTO;

import static com.game.monopoly.constants.ResultMessage.*;

public class ResultMessageMapper {
    public static ResultMessageDTO changeBalanceDTOToResultMessage(ChangeBalanceDTO dto) {
        Long diff = dto.getMoneyDifference();
        String format = diff > 0 ? UPDATE_BALANCE_POSITIVE : UPDATE_BALANCE_NEGATIVE;

        return new ResultMessageDTO(
                String.format(format, dto.getPlayerName(), dto.getMoneyDifference())
        );
    }

    public static ResultMessageDTO buyCardDTOToResultMessage(PerformActionWithCardDTO dto) {
        return new ResultMessageDTO(String.format(BUY_CARD, dto.getPlayerName()));
    }

    public static ResultMessageDTO payForCardDTOToResultMessage(PerformActionWithCardDTO dto) {
        return new ResultMessageDTO(String.format(PAY_FOR_CARD, dto.getPlayerName()));
    }

    public static ResultMessageDTO addPlayerToResultMessage(InitializeSessionDTO sessionDTO) {
        return new ResultMessageDTO(String.format(NEW_PLAYER, sessionDTO.getPlayerName()));
    }
}
