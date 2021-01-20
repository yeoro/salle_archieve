package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.application.ProductService;

@Controller
public class MainController {
	
	@Autowired
	ProductService productService;
	
    @GetMapping("/")
    public String start(Model model) {
    	
    	
		model.addAttribute("productList", productService.getProductList());
    	
        return "main";
    }

    @GetMapping("/home")
    public String home(Model model) {
    	
		model.addAttribute("productList", productService.getProductList());

        return "main";
    }

}
