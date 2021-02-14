package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.application.ChatRoomService;
import com.example.demo.application.ProductService;
import com.example.demo.domain.Login;

@Controller
public class MainController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
    ChatRoomService chatRoomService;
	
    @GetMapping("/")
    public String home(Model model, HttpSession httpSession) {

		model.addAttribute("productList", productService.getProductList());

        return "main";
    }

}
