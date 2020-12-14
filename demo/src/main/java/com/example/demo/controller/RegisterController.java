package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

import com.example.demo.domain.Member;
import com.example.demo.exception.AlreadyExistMember;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.validation.RegisterValidation;


@Controller
public class RegisterController {
	
	  @Autowired
	    MemberMapper memberService;

	    //회원가입 페이지 노출
	    @RequestMapping(value = "/register/main", method = RequestMethod.GET)
	    public String registerAttempt(Model model) {

	        model.addAttribute("member", new Member());
	        return "register/main";
	    }

	    @RequestMapping(value = "/register/done", method = RequestMethod.POST)
	    public String registerHandle(@ModelAttribute @Valid Member member,
	                                 Errors errors) {

	        new RegisterValidation().validate(member, errors);

	        if (errors.hasErrors()) {
	            return "register/main";
	        }

	        try {
	            memberService.insertMember(member);
	            return "register/done";
	        } catch (AlreadyExistMember e) {
	            errors.rejectValue("email", "duplicate");
	            return "register/main";
	        }
	    }

}
