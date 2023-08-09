package fr.mightycode.cpoo.server.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Service
public class RouterService {

    private static final Logger logger = LoggerFactory.getLogger(RouterService.class);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String from, to, body;
    }

    private final RouterStompSessionHandler routerStompSessionHandler;

    RouterService() {
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        routerStompSessionHandler = new RouterStompSessionHandler(webSocketStompClient);
        webSocketStompClient.connectAsync("ws://localhost:8080/router", routerStompSessionHandler);
    }

    public void routeMessage(Message message) {
        logger.info("Routing message {}", message);
        StompSession session = routerStompSessionHandler.getSession();
        if (session == null || !session.isConnected()) {
            logger.error("Not connected to router");
            return;
        }
        session.send("/router/route", message);
    }
}
