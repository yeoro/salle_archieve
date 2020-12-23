package com.example.demo.application;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.mapper.ProductMapper;

@Transactional
@Service
public class ProductService implements ProductMapper {
	
    @Autowired
    ProductMapper productMapper;
    
    private String namespace = "com.example.demo.mapper.ProductMapper";

	@Override
	public void registerProduct(Product product) {
		
	   // Product product = new Product();
		
		//A date-time without a time-zone in the ISO-8601 calendar system,such as 2007-12-03T10:15:30.
		//2020-12-21 13:10:52.467 요런 형식임
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        
        product.setPr_reg_date(ts);

        //Format을 내가 원하는대로 맞춰주기 위해 SimpleDateFormat을 활용해야하고 그래서 Date 객체를 써야함.
        //h2-consle의 parsedatetime을 쓸거기 때문에 아래 parsing은 생략해도 됨
//        Date date = new Date(ts.getTime());
//
//        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
		
		//final TimestampWithTimeZone ts = new TimestampWithTimeZone(new Date().getTime(), 0, (short) 8);
		//https://www.codota.com/code/java/classes/org.h2.api.TimestampWithTimeZone
		
		productMapper.registerProduct(product);

	}

	@Override
	public int getCountProduct() {
		
		return productMapper.getCountProduct();
	}

	@Override
	public void deleteProduct() {
		productMapper.deleteProduct();
	}

}
