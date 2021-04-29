package com.study.login.service.spec;

import com.study.login.domain.dto.MemberDto;

import java.util.List;

public interface AdminService {
    public void updateRole(String memberId, List<String> role);

    public List<MemberDto> findMembers();
}
