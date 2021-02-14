package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.application.ChatRoomService;
import com.example.demo.domain.ChatList;
import com.example.demo.domain.ChatRoom;
import com.example.demo.domain.Login;
import com.example.demo.domain.TimeUtils;

@Controller
public class ChatApplicationController {
	

	@Autowired
	private SimpMessagingTemplate simpMessage;
	
	@Autowired
	private ChatRoomService chatRoomService;

	//채팅으로 거래하기(productInfo 화면)
	@RequestMapping(value="/chatMessage", method=RequestMethod.GET)
	public String getWebSocketWithSockJs(Model model, HttpSession session, 
			@ModelAttribute("chatRoom") ChatRoom chatRoom) throws IOException {
		
		//productInfo화면에서 Chat화면에 전달해줄 parameter
		
		Login login = (Login) session.getAttribute("login");
		String buyerId = login.getEmail();
		String buyerName = login.getNickName();
		chatRoom.setBuyerId(buyerId);
		chatRoom.setBuyerName(buyerName);
		
		
		//이미 chatRoom이 만들어져있는지 확인
		if (chatRoomService.countByChatId(chatRoom.getPr_id(), chatRoom.getBuyerId()) > 0) {
			//get ChatRoomInfo
			ChatRoom chatRoomTemp = chatRoomService.findByChatId(chatRoom.getPr_id(), chatRoom.getBuyerId());
			//load existing chat history
			List<ChatRoom> chatHistory = chatRoomService.readChatHistory(chatRoomTemp);
			//transfer chatHistory Model to View
			model.addAttribute("chatHistory", chatHistory);
			
		} else {
			//chatRoom 생성			
			chatRoomService.addChatRoom(chatRoom);			
			//text file 생성
			chatRoomService.createFile(chatRoom.getPr_id(), chatRoomService.getId(chatRoom.getPr_id(), chatRoom.getBuyerId()));								
		}

			//chatRoom Add 시 생성될 chatId
			chatRoom.setId(chatRoomService.getId(chatRoom.getPr_id(), chatRoom.getBuyerId()));
			
			//chatRoom 객체 Model에 저장해 view로 전달
			model.addAttribute("chatRoomInfo", chatRoom);
		
		return "chatBroadcastProduct";
	}
	
	@MessageMapping("/broadcast")
	public void send(ChatRoom chatRoom) throws IOException {

		chatRoom.setSendTime(TimeUtils.getCurrentTimeStamp());
		//append message to txtFile
		chatRoomService.appendMessage(chatRoom);
		
		int id = chatRoom.getId();
		String url = "/user/" + id + "/queue/messages";
		simpMessage.convertAndSend(url, new ChatRoom(chatRoom.getContent(), chatRoom.getSenderName(), chatRoom.getSendTime(), chatRoom.getSenderId())); 
	}
	
	@RequestMapping("/chatread/chatroom/ajax")
	public void ajaxChatRoomRead(@RequestBody String json) throws IOException {
		JSONObject jsn = new JSONObject(json);
		String idStr = (String) jsn.get("id");
		int id = Integer.parseInt(idStr);
		String flag = (String) jsn.get("flag");
		if (flag.equals("sell")) {
			chatRoomService.updateChatReadSell(id, 1);
		} else {
			chatRoomService.updateChatReadBuy(id, 1);
		}
		
		//		if (!readerId.equals(buyerId)) {
//			chatRoomService.updateChatReadSell(id, 1);
//		} else {
//			chatRoomService.updateChatReadBuy(id, 1);
//		}
	}

	@RequestMapping("/chatread/product/ajax")
	public void ajaxChatProductRead(@RequestBody String json) throws IOException {
		JSONObject jsn = new JSONObject(json);
		String idStr = (String) jsn.get("id");
		int id = Integer.parseInt(idStr);
		chatRoomService.updateChatReadBuy(id, 1);
	}
	
	
	@RequestMapping(value="/chatList", method=RequestMethod.GET)
	public String getChatList(Model model, HttpSession session) {
		
		 Login login = (Login)session.getAttribute("login");
		 String email = login.getEmail();
		 
		 List<ChatList> chatRoomList = chatRoomService.findByEmail(email);		 
		 List<Integer> unreadChatId = chatRoomService.getUnreadChatRoom(email);
		 
		 for (ChatList chatList : chatRoomList) {
			 	
			 if (chatList.getBuyerId().equals(email)) {
				 chatList.setSenderName(chatList.getSellerName());
			 } else {
				 chatList.setSenderName(chatList.getBuyerName());				 
			 }
			 
			 System.out.println("chatList senderName: " + chatList.getSenderName());
			 	
			 if (unreadChatId.size() == 0) {
				 chatList.setChatRoomRead(0);
				} else {
					 for (int ele : unreadChatId) {
						 	if (chatList.getId() == ele) {
						 		System.out.println("unreadChatRoomId: " + ele);
						 		System.out.println("unreadEle: " + ele);
						 		chatList.setChatRoomRead(1);
						 	} else {
						 		chatList.setChatRoomRead(0);
						 	}
				}
		}
			 
			
		}
		 model.addAttribute("chatRoomList", chatRoomList);
		 model.addAttribute("email", email);

		 return "chatList";
	}
	
	@RequestMapping(value="/chatRoom/{pr_id}/{buyerId}", method=RequestMethod.GET)
	public String getChatRoom(@PathVariable Map<String, String> requestVar,
			Model model) throws IOException {
		
		String buyerId = requestVar.get("buyerId");
		int pr_id = Integer.parseInt(requestVar.get("pr_id"));
			
		//read chatHistory
		ChatRoom chatRoomRead = chatRoomService.findByChatId(pr_id, buyerId);
		List<ChatRoom> chatHistory = chatRoomService.readChatHistory(chatRoomRead);
		model.addAttribute("chatHistory", chatHistory);
		
		int id = chatRoomService.getId(pr_id, buyerId);
		String pr_title = chatRoomRead.getPr_title();
		String sellerId = chatRoomRead.getSellerId();
		
		model.addAttribute("id", id);
		model.addAttribute("pr_id", pr_id);
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("sellerId", sellerId);
		model.addAttribute("pr_title", pr_title);
		
		return "chatBroadcastChatRoom";
	}
	
	
	@RequestMapping(value="/chatUnread/ajax", method=RequestMethod.POST)
	@ResponseBody
	public int chatUnread(@RequestBody String json) {
		
		JSONObject jsn = new JSONObject(json);
		
		String email = (String) jsn.get("email");

    		int messages = chatRoomService.getUnreadMessages(email);

		return messages;
	}
	
	
}
