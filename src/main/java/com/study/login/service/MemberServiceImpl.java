package com.study.login.service;

import com.study.login.domain.dto.MemberDto;
import com.study.login.repository.Member;
import com.study.login.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final Encryption encryption;

    public MemberServiceImpl(MemberRepository memberRepository, Encryption encryption) {
        this.memberRepository = memberRepository;
        this.encryption = encryption;
    }

    @Override
    public String save(MemberDto member) {

        try{
            String pw = member.getPassword();
            String salt = encryption.getSalt();
            String encryptedPassword = encryption.getEncryptedPassword(pw, salt);
            ModelMapper modelMapper = new ModelMapper();
            Member memberEntity = modelMapper.map(member, Member.class);

            memberEntity.setPassword(encryptedPassword);
            memberEntity.setSalt(salt);

            memberRepository.save(memberEntity);

            return memberEntity.getId();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public MemberDto find(String id) {
        return null;
    }
}
