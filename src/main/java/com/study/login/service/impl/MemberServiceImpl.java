package com.study.login.service.impl;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.entity.Member;
import com.study.login.repository.MemberRepository;
import com.study.login.service.Encryption;
import com.study.login.service.spec.MemberService;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final Encryption encryption;

    @Override
    public String save(MemberDto member) {
        String pw = member.getPassword();
        String salt = encryption.getSalt();
        String encryptedPassword = encryption.getEncryptedPassword(pw, salt);
        ModelMapper modelMapper = new ModelMapper();
        Member memberEntity = modelMapper.map(member, Member.class);
        String[] roles = member.getId().equals("admin") ? new String[]{"ROLE_ADMIN", "ROLE_MANAGER"} : new String[]{"ROLE_GUEST", "ROLE_VIP"};

        memberEntity.setPassword(encryptedPassword);
        memberEntity.setSalt(salt);
        memberEntity.setRoles(Collections.arrayToList(roles));

        memberRepository.save(memberEntity);

        return memberEntity.getId();
    }

    @Override
    public Member find(String id) {
        Member findMember = memberRepository.findById(id).orElse(null);
        return findMember;
    }

}
