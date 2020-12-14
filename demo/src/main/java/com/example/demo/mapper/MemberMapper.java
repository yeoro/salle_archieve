package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Member;

@Mapper
public interface MemberMapper {
	
    void insertMember(Member member);

    Member memberInfo(String email);


}
