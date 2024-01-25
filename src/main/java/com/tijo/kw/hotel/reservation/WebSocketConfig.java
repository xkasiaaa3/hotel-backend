package com.tijo.kw.hotel.reservation;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/our-websocket")
                .setAllowedOrigins("*")
                .withSockJS(); // Umożliwia korzystanie z SockJS dla przeglądarek, które nie obsługują bezpośrednio WebSockets
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Dodaje prostego brokera wiadomości, przekierowującego wiadomości do kanałów o określonych prefixach
        registry.setApplicationDestinationPrefixes("/ws"); // Ustawia prefiks, który będzie używany do kierowania wiadomości od klienta do serwera
    }
}
