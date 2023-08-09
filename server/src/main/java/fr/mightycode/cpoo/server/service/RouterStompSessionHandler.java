package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Message;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

public class RouterStompSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RouterStompSessionHandler.class);

    private final WebSocketStompClient webSocketStompClient;

    RouterStompSessionHandler(WebSocketStompClient webSocketStompClient) {
        this.webSocketStompClient = webSocketStompClient;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        if (MediaType.APPLICATION_JSON.equals(headers.getContentType()))
            return Message.class;
        throw new RuntimeException("Unexpected");
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders headers) {
        logger.info("Client connected: headers {}", headers);
        session.subscribe("/domain/acme/messages", this);
    }

    @Override
    public void handleFrame(StompHeaders headers, @Nullable Object payload) {
        logger.info("Client received: payload {}, headers {}", payload, headers);
    }

    @Override
    public void handleException(StompSession session, @Nullable StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Client error: exception {}, command {}, payload {}, headers {}", exception.getMessage(), command, payload, headers);
    }

    @SneakyThrows
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("Client transport error: error {}", exception.getMessage());
        if (!session.isConnected()) {
            Thread.sleep(1000);
            logger.info("Trying to reconnect...");
            webSocketStompClient.connectAsync("ws://localhost:8080/router", this);
        }
    }
}
