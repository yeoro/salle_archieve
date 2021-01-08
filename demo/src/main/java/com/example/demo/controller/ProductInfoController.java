package com.example.demo.controller;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.application.ProductService;
import com.example.demo.domain.Member;
import com.example.demo.domain.Product;
import com.example.demo.domain.UuidImgname;
import com.example.demo.validation.SellProductValidation;

@Controller
public class ProductInfoController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Product product;
	
	//TODO: productInfo 뒤 seq 지정해야한다.
	@RequestMapping(value = "/productInfo/{pr_id}", method = RequestMethod.GET)
	public String productInfoGet(Model model, @PathVariable int pr_id) {
		
		//.jsp에서 ${product.pr_title}
		Product productInfo = productService.getProductInfo(pr_id);
		model.addAttribute(productInfo);
		
		//member nickname
		model.addAttribute("nickName",productService.getMemberProductInfo(productInfo.getPr_email()));
		
		//hoursfromupload
	        Timestamp tsClient = Timestamp.valueOf(LocalDateTime.now());
	        long diffTime = tsClient.getTime() - productInfo.getPr_reg_date().getTime();
	        int hours = (int) (diffTime / (1000 * 3600));
	        productInfo.setHoursFromUpload(hours);
			model.addAttribute("hoursFromUpload",productInfo.getHoursFromUpload());
		
		return "product/productInfo";
	}
	
	@RequestMapping(value = "/productInfo/list", method = RequestMethod.GET)
	public String productListGet(Model model) {
		
		model.addAttribute("productList", productService.getProductList());
		
		return "product/productList";
	}

	@RequestMapping(value = "/category/{pr_category}", method = RequestMethod.GET)
	public String productListGet(Model model, @PathVariable String pr_category) {
		
		model.addAttribute("categoryProductList", productService.getCategoryProductList(pr_category));
		
		return "product/categoryProductList";
	}
	
	@RequestMapping(value = "/search/result", method = RequestMethod.GET) 
	public String searchGet(@RequestParam("searchWord") String searchWord, Model model) {
		
		//String searchWord = searchWordRaw.replaceAll("\\s", "");
		model.addAttribute("searchProductList", productService.search(searchWord));
		return "product/searchResult";
	}
    
}
