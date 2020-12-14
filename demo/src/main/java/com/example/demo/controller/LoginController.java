package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.MemberService;
import com.example.demo.domain.Login;
import com.example.demo.domain.Member;
import com.example.demo.exception.IncorrectPasswordException;
import com.example.demo.exception.UnregisteredMemberException;

@Controller
public class LoginController {
	
    @Autowired
    MemberService memberService;

    //회원가입 페이지 노출
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAttempt(@ModelAttribute("member") Member member) {

        return "login";
    }

    @RequestMapping(value = "/login/done", method = RequestMethod.POST)
    public String loginHandle(Member member, HttpSession httpSession, Model model) {

    	Member memberInfo;
    	
        try {
            memberInfo = memberService.loginMember(member);
        } catch (IncorrectPasswordException passwordException) {
            return "redirect:/login";
        } catch (UnregisteredMemberException memberException) {
            return "redirect:/register/main";
        }
        
        model.addAttribute("member", memberInfo);

        return "home";
    }

}
