package com.study.login.service;

import com.study.login.domain.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Override
    public String save(MemberDto member) {
        return member.getId();
    }

    @Override
    public MemberDto find(String id) {
        return null;
    }
}
