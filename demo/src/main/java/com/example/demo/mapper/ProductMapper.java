package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Product;

@Mapper
public interface ProductMapper {
	
	void registerProduct(Product product);
	
	int getCountProduct();

	void deleteProduct();

}
