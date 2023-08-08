package fr.mightycode.cpoo.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Service
public class MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(MessagingService.class);

    MessagingService() {
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        RouterStompSessionHandler routerStompSessionHandler = new RouterStompSessionHandler(webSocketStompClient);
        webSocketStompClient.connectAsync("ws://localhost:8080/router", routerStompSessionHandler);
    }
}
