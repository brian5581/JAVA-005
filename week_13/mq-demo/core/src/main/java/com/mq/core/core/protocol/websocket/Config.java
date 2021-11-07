package com.mq.core.core.protocol.websocket;

import com.mq.core.core.messagequeue.Broker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author binary
 */
@Configuration
@EnableWebSocket
public class Config implements WebSocketConfigurer {

    private final Broker broker;

    public Config(Broker broker) {
        this.broker = broker;
    }

    @Bean
    public WebSocketHandler producerHandler() {
        return new ProducerHandler(broker);
    }

    @Bean
    public WebSocketHandler consumerHandler() {
        return new ConsumerHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(producerHandler(), "/producer")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");

        webSocketHandlerRegistry.addHandler(producerHandler(), "/consumer")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");
    }
}
