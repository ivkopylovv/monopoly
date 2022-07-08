package com.game.monopoly.entity.compositekey;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class CompanyCardId implements Serializable {
    private Long cardId;
    private String sessionId;
}
