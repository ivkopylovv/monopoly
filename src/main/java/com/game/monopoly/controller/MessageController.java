package com.game.monopoly.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting() throws InterruptedException {
        Thread.sleep(1000); // simulated delay
        return "hello";
    }

}
