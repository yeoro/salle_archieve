package com.example.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.domain.Member;
import com.example.demo.validation.RegisterValidation;

public class RegisterValidation implements Validator {
	
	   private static final String EMAIL_REG_EXP =
	            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9]+)*@" +
	                    "[A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	    private Pattern pattern;

	    public RegisterValidation() {
	        pattern = Pattern.compile(EMAIL_REG_EXP);
	    }

	    @Override
	    public boolean supports(Class<?> aClass) {
	        return RegisterValidation.class.isAssignableFrom(aClass);
	    }

	    @Override
	    public void validate(Object target, Errors errors) {
	        //target : 검사 대상 객체 참조(커맨드 객체) = Member 객체
	        //errors : target 검사 후 에러 코드를 저장하는 객체(스프링이 만들어서 전달해 준 것)
	        Member member = (Member) target;

	        //이메일 없거나 잘못된 양식일 경우
	        if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
	            errors.rejectValue("email", "required");
	        } else {
	            Matcher matcher = pattern.matcher(member.getEmail());
	            if (!matcher.matches()) {
	                errors.rejectValue("email", "incorrectFormat");
	            }
	        }

	        //입력값이 비었을 때
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nickName", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNum", "required");

	        //비밀번호 확인
	        if (!member.getConfirmPassword().equals(member.getPassword())) {
	            errors.rejectValue("confirmPassword", "unmatch");
	        }
	    }

}
