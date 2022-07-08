package com.game.monopoly.entity.compositekey;

<<<<<<< Updated upstream
import lombok.Data;
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> Stashed changes

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
<<<<<<< Updated upstream
=======
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> Stashed changes
public class CompanyCardId implements Serializable {
    private Long cardId;
    private String sessionId;
}
