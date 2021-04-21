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

    @PostMapping("login/session")
    public String login(@RequestBody MemberDto member, HttpSession session) {
        memberService.login(member, session);
        return "main";
    }

    @PostMapping("login/token")
    public String login(@RequestBody Map<String,Object> params) {
        memberService.login(params);
        return "main";
    }

    @GetMapping("main/session")
    public String mainPageBySession(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession().getAttribute("loginId");
        model.addAttribute("data", loginId);
        return "main";
    }

    @GetMapping("main/token")
    public String mainPageByToken(Model model, HttpServletRequest request) {
        String loginId = (String) request.getAttribute("loginId");
        model.addAttribute("data", loginId);
        return "main";
    }
}
