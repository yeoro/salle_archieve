package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.application.MemberService;
import com.example.demo.application.ProductService;
import com.example.demo.domain.Login;
import com.example.demo.exception.IncorrectPasswordException;
import com.example.demo.exception.UnregisteredMemberException;

@Controller
public class LoginController {
	
    @Autowired
    MemberService memberService;
    
    @Autowired
    ProductService productService;

    //회원가입 페이지 노출
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAttempt(@ModelAttribute("login") Login login) {

        return "login";
    }

    @RequestMapping(value = "/login/done", method = RequestMethod.POST)
    public String loginHandle(@ModelAttribute Login login, HttpSession httpSession, Model model) {

    	Login loginInfo;
    	
        try {
            loginInfo = memberService.loginMember(login);
        } catch (IncorrectPasswordException passwordException) {
        	model.addAttribute("login", null);
            return "login";
        } catch (UnregisteredMemberException memberException) {
        	model.addAttribute("login", null);
            return "login";
        }
        
        model.addAttribute("productList", productService.getProductList());
        return "main";
    }

}
