

package com.example.demo;

import java.util.Locale;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	//SqlSessionFactory 설정
	//@Bean은 스프링에 필요한 객체를 생성. sqlSessionFactory() : MyBatis의 SqlSessionFactory를 반환해준다. 스프링부트가 실행될 때
	//DataSource 객체를 이 메서드 실행 시 주입해서 결과를 만들고, 그 결과를 스프링 내 빈으로 사용한다.
	//SqlSessionFactoryBean은 스프링의 FactoryBean 인터페이스를 구현한다. 스프링이 SqlSessionFactoryBean 자체를 생성하는 것이 아니라
	//팩토리에서 getObject()메서드를 호출한 결과를 리턴한다. 스프링은 시작 시점에 SqlSessionFactory를 빌드하고 sqlSessionFactory라는
	//이름으로 저장한다.


	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		//Member 클래스 경로
		sessionFactoryBean.setTypeAliasesPackage("com.example.demo.domain");
		return sessionFactoryBean.getObject();
	}

	
	@Bean
	public MessageSource messageSource() {
		Locale.setDefault(Locale.KOREA);
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setBasename("label/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}


	public static void main(String[] args) {
    	SpringApplication.run(DemoApplication.class, args);
    }

}

