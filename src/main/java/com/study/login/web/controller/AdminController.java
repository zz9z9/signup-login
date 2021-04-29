package com.study.login.web.controller;

import com.study.login.domain.dto.MemberDto;
import com.study.login.service.spec.AdminService;
import com.study.login.service.spec.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("")
    public String adminMainPage() {
        return "/admin/main";
    }

    @GetMapping("/manage/members")
    public String manageMembers(Model model) {
        List<MemberDto> members = adminService.findMembers();
        model.addAttribute("members",members);

        return "/admin/member_list";
    }

    @PostMapping("/update/role")
    public String updateRole(@RequestBody Map<String,Object> param) {
        String memberId = (String) param.get("memberId");
        List<String> roles = (List<String>) param.get("roles");

        adminService.updateRole(memberId, roles);

        return "redirect:/admin/manage/members";
    }
}
