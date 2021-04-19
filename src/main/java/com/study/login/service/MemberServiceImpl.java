package com.study.login.service;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.Member;
import com.study.login.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final Encryption encryption;

    public MemberServiceImpl(MemberRepository memberRepository, Encryption encryption) {
        this.memberRepository = memberRepository;
        this.encryption = encryption;
    }

    @Override
    public String save(MemberDto member) {
        String pw = member.getPassword();
        String salt = encryption.getSalt();
        String encryptedPassword = encryption.getEncryptedPassword(pw, salt);
        ModelMapper modelMapper = new ModelMapper();
        Member memberEntity = modelMapper.map(member, Member.class);

        memberEntity.setPassword(encryptedPassword);
        memberEntity.setSalt(salt);

        memberRepository.save(memberEntity);

        return memberEntity.getId();
    }

    @Override
    public Member find(String id) {
        Member findMember = memberRepository.findById(id);
        return findMember;
    }

    @Override
    public void login(MemberDto member, HttpSession session) {
        String id = member.getId();
        String pw = member.getPassword();

        Member findMember = find(id);

        System.out.println("passedMember : "+member);
        System.out.println("findMember : " + findMember);

        if (findMember != null) {
            String storedSalt = findMember.getSalt();
            String storedEncryptedPw = findMember.getPassword();
            String userPw = encryption.getEncryptedPassword(pw, storedSalt);

            System.out.println("userPw : "+userPw);

            if(storedEncryptedPw.equals(userPw)) {
                System.out.println("비밀번호 일치!");

                session.setAttribute("loginId", id);
                session.setMaxInactiveInterval(60*10); // 세션 지속 시간
            } else {
                System.out.println("비밀번호 불일치!");
            }
        } else {
            System.out.println("일치하는 회원정보 없음 !");
        }
    }
}
