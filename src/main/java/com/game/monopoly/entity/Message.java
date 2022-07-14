package com.game.monopoly.entity;

import com.game.monopoly.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "sender_id")
    private Player sender;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "receiver_id")
    private Player receiver;

    @Column(name = "type", nullable = false)
    @Enumerated(STRING)
    private MessageType type;


}
