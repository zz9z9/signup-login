package com.study.login.service.spec;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.entity.Member;

public interface MemberService {

    String save(MemberDto member);

    Member find(String id);
}
