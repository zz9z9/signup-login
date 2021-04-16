package com.study.login.service;

import com.study.login.domain.dto.MemberDto;

public interface MemberService {

    String save(MemberDto member);

    MemberDto find(String id);
}
