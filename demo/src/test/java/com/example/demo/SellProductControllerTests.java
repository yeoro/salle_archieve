package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.application.ProductService;
import com.example.demo.domain.Product;


@RunWith(MockitoJUnitRunner.class)
class SellProductControllerTests {
	
	@Mock
	ProductService productService;
	
	/*@Test
	void testRegisterProduct() {
		
		MockitoAnnotations.initMocks(this);
		
		//assertThat(product.getPr_reg_date() != null);
		productService.deleteProduct();
		productService.registerProduct();
		
		assertTrue(productService.getCountProduct()==1);
		
//		product.setPr_reg_date(Timestamp.valueOf((LocalDateTime.now())));
		 product.setEmail("testsellpr@gmail.com");
		product.setPr_category("디지털/가전");
		product.setPr_detail("detail");
		product.setPr_img("img");
		product.setPr_price(101010);
		product.setPr_quality("qual");
		product.setPr_region("seoul");
		product.setPr_title("title");
		
		Product productSql = productService.registerProduct(product); 
		
	}  */


}
