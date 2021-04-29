package com.study.login.service.impl;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.entity.Member;
import com.study.login.security.jwt.JwtTokenProvider;
import com.study.login.service.Encryption;
import com.study.login.service.spec.LoginService;
import com.study.login.service.spec.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final MemberService memberService;
    private final Encryption encryption;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void sessionLogin(MemberDto member, HttpSession session) {
        String id = member.getId();
        String pw = member.getPassword();
        Member findMember = memberService.find(id);

        if (findMember != null) {
            if(isRightPassword(findMember, pw)) {
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

    @Override
    public String tokenLogin(Map<String, Object> params) {
        String id = (String) params.get("id");
        String pw = (String) params.get("password");
        Member findMember = memberService.find(id);
        String jwtToken = null;

        if (findMember != null) {
            if(isRightPassword(findMember, pw)) {
                System.out.println("비밀번호 일치! 토큰을 생성합니다");
                jwtToken = tokenProvider.createToken(findMember.getUsername(), findMember.getRoles());
            } else {
                System.out.println("비밀번호 불일치!");
            }
        } else {
            System.out.println("일치하는 회원정보 없음 !");
        }

        return jwtToken;
    }

    private boolean isRightPassword(Member findMember, String passedPw) {
        String storedSalt = findMember.getSalt();
        String storedEncryptedPw = findMember.getPassword();
        String userPw = encryption.getEncryptedPassword(passedPw, storedSalt);

        return storedEncryptedPw.equals(userPw);
    }
}
