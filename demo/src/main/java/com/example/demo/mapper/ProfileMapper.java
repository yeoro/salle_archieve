package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Member;
import com.example.demo.domain.Product;

@Mapper
public interface ProfileMapper {

	//내 상점 판매자 등록글 가져오기
	List<Product> getSellerProductList(String email);
	
	//Total 판매상품 가져오기
	int getTotalProduct(String email);
	
}
