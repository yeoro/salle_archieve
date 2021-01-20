package com.example.demo.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketBrokerConfig.class);

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//for target broker destination
		registry.enableSimpleBroker("/topic");
		//for application target destination
		registry.setApplicationDestinationPrefixes("/app");
		//user destination provides ability to have unique user queue
		//registry.setUserDestinationPrefix("/user");
		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		registry.addEndpoint("/broadcast")
		//when browser or network cause problems fallback allows realtime connectivity 
				.withSockJS()
				.setHeartbeatTime(60_000);
			//.setAllowedOrigins("*")
				
		WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	}

//	@Override
//	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//		
//		DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
//		resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		converter.setObjectMapper(new ObjectMapper());
//		converter.setContentTypeResolver(resolver);
//		messageConverters.add(converter);
//		
//		return false;
//	}


	
	
	
	

	
	

}
