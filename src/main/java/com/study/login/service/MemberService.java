package com.study.login.service;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.Member;

import javax.servlet.http.HttpSession;

public interface MemberService {

    String save(MemberDto member);

    Member find(String id);

    void login(MemberDto member, HttpSession session);
}
