package com.example.demo.member.repository;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberRepository {

    @Insert("insert into members (userid,passwd,name,email) values (#{userid},#{passwd},#{name},#{email})")
    int insertMember(MemberDTO member);

    @Select("select * from members where userid = #{userid}")
    Member findByUserid(String userid);

    @Select("select count(userid) from members where userid = #{userid}")
    int countByUserid(String userid);

    @Select("select count(email) from members where email = #{email}")
    int countByEmail(String email);
}
