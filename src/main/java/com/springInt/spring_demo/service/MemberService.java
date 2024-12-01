package com.springInt.spring_demo.service;

import com.springInt.spring_demo.domain.Member;
import com.springInt.spring_demo.repository.MemberRepository;
import com.springInt.spring_demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> result = memberRepository.findByUsername(member.getName());
//        result.ifPresent(m-> {
//            throw new IllegalAccessException("이미 존재하는 회원입니다.")
//        });
        memberRepository.findByUsername(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // 함수로 extract 하는키 cmd+opt+M
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     * @return
     */

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
