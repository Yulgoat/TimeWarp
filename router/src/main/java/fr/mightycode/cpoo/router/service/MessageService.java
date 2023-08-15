package fr.mightycode.cpoo.router.service;

import fr.mightycode.cpoo.router.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

  private final SimpMessagingTemplate messagingTemplate;

  public MessageService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void routeMessage(Message message) {
    logger.info("Routing message {}", message);
    // TODO: check destination domain validity
    String destinationDomain = message.getTo().split("@")[1];
    messagingTemplate.convertAndSend("/domain/" + destinationDomain + "/messages", message);
  }
}
