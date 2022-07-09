package com.game.monopoly.mapper;

import com.game.monopoly.dto.request.*;

import java.util.List;

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

    public static ResultMessageDTO addPlayerToResultMessage(ActionWithSessionDTO sessionDTO) {
        return new ResultMessageDTO(String.format(NEW_PLAYER, sessionDTO.getPlayerName()));
    }

    public static ResultDiceRollMessageDTO randomSteps(ActionWithSessionDTO sessionDTO,
                                                       List<Long> rollResults) {
        return new ResultDiceRollMessageDTO(String.format(DICE_ROLL_RESULT, sessionDTO.getPlayerName(),
                rollResults.get(0) + rollResults.get(1), rollResults.get(0), rollResults.get(1)),
                rollResults.get(0), rollResults.get(1));
    }
}
