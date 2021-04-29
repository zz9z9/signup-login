package com.study.login.web.controller;

import com.study.login.domain.dto.MemberDto;
import com.study.login.domain.dto.SessionUser;
import com.study.login.service.spec.LoginService;
import com.study.login.service.spec.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if(user != null) {
//            return "redirect:/main/third-party";
//        }

        return "index";
    }

    @PostMapping("login/session")
    public String login(@RequestBody MemberDto member, HttpSession session) {
        loginService.sessionLogin(member, session);
        return "redirect:/main/session";
    }

    @PostMapping("login/token")
    public String login(@RequestBody Map<String,Object> params, HttpServletResponse response) {
        String jwtToken = loginService.tokenLogin(params);

        if(jwtToken!=null) {
            System.out.println("jwtToken = " + jwtToken);
            Cookie cookie = new Cookie("jwtToken", jwtToken);
            cookie.setPath("/");
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
        }

        return "main";
    }

    @GetMapping("/success/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("LoginController.test");

        return "test";
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
