package com.example.demo.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.application.ProductService;
import com.example.demo.domain.Member;
import com.example.demo.domain.Product;
import com.example.demo.domain.UuidImgname;
import com.example.demo.validation.SellProductValidation;

@Controller
public class MystoreController {
	
	
	//당근마켓 상품등록 목업 html 테스트
	@RequestMapping(value = "/mystore", method = RequestMethod.GET)
	public String mystoreget() {
		
		return "mystore";
	}
    
    

}
