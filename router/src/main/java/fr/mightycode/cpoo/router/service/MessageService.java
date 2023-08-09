package fr.mightycode.cpoo.router.service;

import fr.mightycode.cpoo.router.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageToDomain(String domain, Message message) {
        String destination = "/domain/" + domain + "/messages";
        messagingTemplate.convertAndSend(destination, message);
    }
}
