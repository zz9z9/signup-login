package com.study.login.web;

import com.study.login.domain.dto.MemberDto;
import com.study.login.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup")
    public String signUp(@RequestBody Map<String,Object> params) {
        String id = (String) params.get("userId");
        String pw = (String) params.get("userPw");

        memberService.save(new MemberDto(id,pw));

        return "redirect:/";
    }

    @PostMapping("login")
    public String login(@RequestBody Map<String,Object> params, HttpSession session) {
        String id = (String) params.get("userId");
        String pw = (String) params.get("userPw");
        MemberDto member = new MemberDto(id,pw);

        memberService.login(member, session);

        return "redirect:/main";
    }

    @GetMapping("main")
    public String hello(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession().getAttribute("loginId");
        model.addAttribute("data", loginId);
        return "main";
    }
}
