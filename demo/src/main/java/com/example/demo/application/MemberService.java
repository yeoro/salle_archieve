package com.example.demo.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Login;
import com.example.demo.domain.Member;
import com.example.demo.exception.IncorrectPasswordException;
import com.example.demo.exception.UnregisteredMemberException;
import com.example.demo.mapper.MemberMapper;

@Transactional
@Service
public class MemberService implements MemberMapper {
	
    @Autowired
    MemberMapper memberMapper;

    @Override
    public void insertMember(Member member) {
    	
        memberMapper.insertMember(member);
    }

    @Override
    public Member memberInfo(String email) {

        return memberMapper.memberInfo(email);
    }


    public Login loginMember(Login login) {

        Member memberInfo = memberMapper.memberInfo(login.getEmail());
                
        if (memberInfo == null) {
            throw new UnregisteredMemberException();
        } else {
            if (memberInfo.getPassword().equals(login.getPassword())) {
            } else {
                throw new IncorrectPasswordException();
            }
        }
        
        login.setNickName(memberInfo.getNickName()); 
        
        //TODO: 이메일 기억하기, 로그인 유지 구현
//        Cookie

        return login;
    }

}
