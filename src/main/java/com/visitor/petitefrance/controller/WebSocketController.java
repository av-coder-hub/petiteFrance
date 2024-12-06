package com.visitor.petitefrance.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.visitor.petitefrance.model.Destination;

@Controller
public class WebSocketController {
    @MessageMapping("/send-destination") // From the frontend
    @SendTo("/topic/destinations")      // Broadcast to all subscribers
    public Destination broadcastDestination(Destination destination) {
        return destination; // Broadcasts the destination to connected clients
    }
}
