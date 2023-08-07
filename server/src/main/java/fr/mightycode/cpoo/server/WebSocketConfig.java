package fr.mightycode.cpoo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class WebSocketConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Bean
    public WebSocketStompClient webSocketStompClient(WebSocketClient webSocketClient, StompSessionHandler stompSessionHandler) {
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient);
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        webSocketStompClient.connectAsync("ws://localhost:8080/messageRouter", stompSessionHandler);
        return webSocketStompClient;
    }

    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public MyStompSessionHandler stompSessionHandler() {
        return new MyStompSessionHandler();
    }
}
