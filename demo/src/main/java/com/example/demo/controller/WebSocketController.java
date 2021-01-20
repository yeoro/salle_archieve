package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.ChatMessage;

@Controller
public class WebSocketController {
	
	@RequestMapping(value="/chat", method = RequestMethod.GET)
	public String getChat() {
		
		return "wsBroadcast";
	}

	@RequestMapping(value="/stomp-broadcast", method = RequestMethod.GET)
	public String getWebsocketBroadcastStomp() {
		
		//STOMP
		return "stompBroadcast";
		
	}

	@RequestMapping(value="/sockjs-broadcast", method = RequestMethod.GET)
	public String getWebsocketBroadcastSockjs() {
		
		//SockJS
		return "sockJSBroadcast";
	}
	
	@MessageMapping("/broadcast")
	@SendTo("/topic/messages")
	public ChatMessage send(ChatMessage chatMessage) {
		
		return new ChatMessage(chatMessage.getChatId(), chatMessage.getContent(), "ALL");
	}
}
