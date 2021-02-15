<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ChatList</title>
<link rel="stylesheet" href="/resources/css/chatList.css">
</head>
<body>

<%@include file="home.jsp" %>

	<div class="wrapper">
	
	</div>
	 
	 <script type="text/javascript">
	 
	 //home.jsp에 있음
	 	var email = document.getElementById('emailInput').value;
		$(document).ready(initialize());
		
		function initialize() {
			getChatList();
			unreadAlertInfinite();
			
		}
		 
		function getChatList() {
			console.log("getChatList inprocess");
			$.ajax({
				url: "/chatList/ajax",
				type: "POST",
				data: JSON.stringify({
					email: email
				}),
				contentType: "application/json",
				success: function(data) {

					 var parsed = JSON.parse(data);
					 var length = parsed.chatList.length;
					 for (var i = 0; i < length; i++) {
						 addChatDivImg(i, parsed.chatList[i].pr_img_1);
					 }
				}
			});
		}
		
		 function getUnread() {

			 	console.log("getUnread inprocess");
				 $.ajax({
					 url:"/chatUnreadMessage/ajax",
					 type: "POST",
					 data: JSON.stringify({
						 email: email
					 }),
					 contentType: "application/json",
					 success: function(data) {
						 var parsed = JSON.parse(data);
						 console.log("parsedSednerName: " + parsed.chatList[0].senderName);	
						 console.log("length: " + parsed.chatList.length);
						 var length = parsed.chatList.length;
						 
						 for (var i = 0; i < length; i++) {
							$('.wrapSellerTitle' + i).html('');
						 	addChatList(parsed.chatList[i].pr_id, parsed.chatList[i].buyerId, parsed.chatList[i].senderName, parsed.chatList[i].pr_title, parsed.chatList[i].messageUnread, i);
						 }
					 }
			 });
		 }
	 	
	 	function unreadAlertInfinite() {
	 		setInterval(() => {
			 	getUnread();				
			}, 1000);
	 	}
	 	
	 	//0: pr_id, 1: buyerId, 2: pr_img_1, 3: senderName, 4: pr_title, 5: messageUnread
	 	function addChatList(pr_id, buyerId, senderName, pr_title, messageUnread, idx) {

	 		var str =
	 		'<a href="/chatRoom/' + 
	 		pr_id +
	 		'/' + 
	 		buyerId + 
	 		'">' +
	 		'<h3><span id="sellerName">' + 
	 		senderName +
	 		'&nbsp;</span>' +
	 		'<span id="title">' + 
	 		pr_title + 
	 		'</span><span id="message">' + 
	 		messageUnread+'</span></h3></div></a>';
	 			
	 			console.log("appendStr: " + str);
	 		 
	 		 $('.wrapSellerTitle' + idx).append(str);
	 	} 
	 	
	 	function addChatDivImg(idx, img) {
	 			$(document.body).append('<div class= chatMessageInfo' + idx + '><div class="wrapPr_img"><img class="pr_img" src="' + img + '"></div><div class="wrapSellerTitle' +
	 					idx +
	 					'"></div></div>');
	 	}
	 	

	 	
	 </script>
</body>
</html>