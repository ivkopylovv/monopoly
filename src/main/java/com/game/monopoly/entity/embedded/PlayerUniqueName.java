package com.game.monopoly.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerUniqueName implements Serializable {
    private String sessionId;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerUniqueName)) return false;
        PlayerUniqueName that = (PlayerUniqueName) o;
        return this.sessionId != null && this.sessionId.equals(that.sessionId)
                && this.name != null && this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, name);
    }
}
