package com.study.login.web;

import com.study.login.domain.dto.MemberDto;
import com.study.login.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("signup")
    public String signUp(@RequestBody Map<String,Object> params) {
        String id = (String) params.get("userId");
        String pw = (String) params.get("userPw");

        memberService.save(new MemberDto(id,pw));

        return id;
    }

}
