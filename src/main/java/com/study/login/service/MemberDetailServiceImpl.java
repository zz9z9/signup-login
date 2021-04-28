package com.study.login.service;

import com.study.login.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberDetailServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pk) throws UsernameNotFoundException {
        return memberRepository.findById(pk).orElse(null);
    }
}
