package com.example.demo.member.service;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.dto.LoginDTO;
import com.example.demo.member.domain.dto.MemberDTO;

public interface MemberService {

    boolean newMember(MemberDTO member);
    Member loginMember(LoginDTO member);

}

