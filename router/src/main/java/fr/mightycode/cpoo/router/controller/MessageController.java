package fr.mightycode.cpoo.router.controller;

import fr.mightycode.cpoo.router.model.Message;
import fr.mightycode.cpoo.router.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    @MessageMapping("/route")
    public void route(Message message) {
        logger.info("Routing message {}", message);
        messageService.routeMessage(message);
    }
}
