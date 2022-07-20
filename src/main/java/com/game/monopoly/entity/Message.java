package com.game.monopoly.entity;

import com.game.monopoly.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "type", nullable = false)
    @Enumerated(STRING)
    private MessageType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return this.id != null && this.id.equals(message.id)
                && this.content != null && this.content.equals(message.content)
                && Objects.equals(this.sender, message.sender)
                && Objects.equals(this.receiver, message.receiver)
                && this.type != null && this.type == message.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, sender, receiver, type);
    }
}
