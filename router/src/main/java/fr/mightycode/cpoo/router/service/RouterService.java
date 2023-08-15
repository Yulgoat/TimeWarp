package fr.mightycode.cpoo.router.service;

import fr.mightycode.cpoo.router.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;

@Service
public class RouterService {

  private static final Logger logger = LoggerFactory.getLogger(RouterService.class);

  private final SimpMessagingTemplate messagingTemplate;

  public RouterService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void routeMessage(Message message, StompHeaderAccessor accessor) {
    logger.info("Routing message {}", message);
    // TODO: check destination domain validity
    String destinationDomain = message.getTo().split("@")[1];
    messagingTemplate.convertAndSend("/domain/" + destinationDomain + "/messages", message);
  }
}
