package su.vistar.springwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author dantonov
 */

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry ser) {
        ser.addEndpoint("/socket").withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry mbr){
        mbr.enableSimpleBroker("/topic/");
        mbr.setApplicationDestinationPrefixes("/app");
    }
    
    
    
}
