package com.study.login.service.spec;

import com.study.login.domain.dto.MemberDto;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface LoginService {
    void sessionLogin(MemberDto member, HttpSession session);

    String tokenLogin(Map<String,Object> params);
}
