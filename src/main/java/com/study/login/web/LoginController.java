package com.study.login.web;

import com.study.login.domain.dto.MemberDto;
import com.study.login.domain.dto.SessionUser;
import com.study.login.service.MemberService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.Map;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            return "redirect:/main/third-party";
        }

        return "index";
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
        memberService.sessionLogin(member, session);
        return "redirect:/main/session";
    }

    @PostMapping("login/token")
    public String login(@RequestBody Map<String,Object> params, HttpServletResponse response) {
        String jwtToken = memberService.tokenLogin(params);
        Cookie cookie = new Cookie("jwtToken", jwtToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

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

    @GetMapping("main/third-party")
    public String mainPageByOAuth(Model model, HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        model.addAttribute("data", user.getName());

        return "main";
    }
}
