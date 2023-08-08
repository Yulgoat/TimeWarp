package fr.mightycode.cpoo.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Service
public class MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(MessagingService.class);

    private final RouterStompSessionHandler routerStompSessionHandler;

    private final WebSocketStompClient webSocketStompClient;

    MessagingService() {
        routerStompSessionHandler = new RouterStompSessionHandler();
        webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        webSocketStompClient.connectAsync("ws://localhost:8080/router", routerStompSessionHandler);
    }

    @Scheduled(cron = "* * * * * ?")
    void reconnect() {
        logger.info("### reconnect");
        if (!routerStompSessionHandler.getStompSession().isConnected())
            webSocketStompClient.connectAsync("ws://localhost:8080/router", routerStompSessionHandler);
    }
}
