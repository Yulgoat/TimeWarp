package fr.mightycode.cpoo.router;

import fr.mightycode.cpoo.router.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessagingService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageToDomain(String domain, Message message) {
        String destination = "/domain/" + domain + "/incomingMessages";
        messagingTemplate.convertAndSend(destination, message);
    }
}
