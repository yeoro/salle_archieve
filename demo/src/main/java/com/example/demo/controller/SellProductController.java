package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.application.ProductService;
import com.example.demo.domain.Member;
import com.example.demo.domain.Product;
import com.example.demo.domain.UuidImgname;
import com.example.demo.validation.SellProductValidation;

@Controller
public class SellProductController {
	
	@Autowired
    ProductService productService;
	
	@Autowired
	UuidImgname uuidImgname;

	@RequestMapping(value = "/sellproduct", method = RequestMethod.GET)
    public String sellAttempt(Model model) {

    	model.addAttribute("product", new Product());	
        return "sell";
    }
    
    @RequestMapping(value = "/sellproduct/done", method = RequestMethod.POST)
    public String sellHandle(@ModelAttribute @Valid Product product, @RequestParam("pr_img_files")
     List<MultipartFile> file, Errors errors, HttpSession httpSession) throws Exception {
    	   	
    	int reps = file.size();
    	
    	for(int i = 0; i < reps; i--) {
    		String filepath = file.get(i).getOriginalFilename();
    		file.get(i).transferTo(new File("C:\\Users\\klyhy\\git\\salle_eclipse_v1\\demo\\src\\main\\webapp\\"
    				+ filepath));
    		String filename = uuidImgname.makeFilename(filepath);
    		product.setPr_img_(filename);
    	}
    	
    	//파일 업로드 작업 수행(destination에 파일을 보내줌)
    	
    	

    	new SellProductValidation().validate(product, errors);
    	
    	if (errors.hasErrors()) {
    		return "redirect:/sellproduct";
    	}
    	
    	Member member = (Member) httpSession.getAttribute("member");
    	product.setEmail(member.getEmail());
    	
    	productService.registerProduct(product);

    	return "home";
    }
    
    //주소검색 API URL 연동
	@RequestMapping(value = "/sell_region", method = RequestMethod.GET)
    public String sellRegionGet() {

        return "sell_region";
    }
	
    
    

}
