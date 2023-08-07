package fr.mightycode.cpoo.router.controller;

import fr.mightycode.cpoo.router.MessagingService;
import fr.mightycode.cpoo.router.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    MessagingService messagingService;

    @MessageMapping("/routeMessage")
    public void route(Message message) {
        String destinationDomain = message.getTo().split("@")[1];
        messagingService.sendMessageToDomain(destinationDomain, message);
    }
}
