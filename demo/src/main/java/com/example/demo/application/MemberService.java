package com.example.demo.application;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Login;
import com.example.demo.domain.Member;
import com.example.demo.exception.AlreadyExistMember;
import com.example.demo.exception.IncorrectPasswordException;
import com.example.demo.exception.UnregisteredMemberException;
import com.example.demo.mapper.MemberMapper;

@Transactional
@Service
public class MemberService implements MemberMapper {
	
    @Autowired
    MemberMapper memberMapper;

    private SqlSession sqlSession;
    private String namespace = "com.example.demo.mapper.MemberMapper";

    @Override
    public void insertMember(Member member) {

        if (memberMapper.memberInfo(member.getEmail()) != null) {
            throw new AlreadyExistMember();
        }
        memberMapper.insertMember(member);
    }

    @Override
    public Member memberInfo(String email) {

        return sqlSession.selectOne(namespace, memberMapper.memberInfo(email));
    }


    public Member loginMember(Member member) {

        Member memberInfo = memberMapper.memberInfo(member.getEmail());
                
        if (memberInfo == null) {
            throw new UnregisteredMemberException();
        } else {
            if (memberInfo.getPassword().equals(member.getPassword())) {
            } else {
                throw new IncorrectPasswordException();
            }
        }
        
        //TODO: 이메일 기억하기, 로그인 유지 구현
//        Cookie


        return memberInfo;
    }

}
