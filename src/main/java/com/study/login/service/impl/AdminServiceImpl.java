package com.study.login.service.impl;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.MemberRepository;
import com.study.login.repository.entity.Member;
import com.study.login.service.spec.AdminService;
import com.study.login.service.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
   // private final MemberService memberService;

    @Override
    public void updateRole(String memberId, List<String> role) {
      // Member findMember = memberRepository.findById(memberId).orElse(null);
       memberRepository.findById(memberId).map(member -> {
            member.setRoles(role);
            return memberRepository.save(member);
       }).orElse(null);
    }

    @Override
    public List<MemberDto> findMembers() {
        List<Member> findMembers = memberRepository.findAll();
        System.out.println("findMembers = " + findMembers);

        return ObjectMapperUtils.mapAll(findMembers, MemberDto.class);
    }
}
