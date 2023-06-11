package com.example.dotoring.service;

import com.example.dotoring.domain.Member;
import com.example.dotoring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMember(Long id){
        Member member = memberRepository.findById(id).get();

        return member;
    }

    public Member saveMember(String nickname){
        Member member = Member.builder()
                .username(nickname)
                .build();

        Member memberEntity = memberRepository.save(member);

        return memberEntity;
    }
}
