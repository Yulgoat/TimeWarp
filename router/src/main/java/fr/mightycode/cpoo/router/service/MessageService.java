package fr.mightycode.cpoo.router.service;

import fr.mightycode.cpoo.router.model.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void routeMessage(Message message) {
        // TODO: check destination domain validity
        String destinationDomain = message.getTo().split("@")[1];
        messagingTemplate.convertAndSend("/domain/" + destinationDomain + "/messages", message);
    }
}
