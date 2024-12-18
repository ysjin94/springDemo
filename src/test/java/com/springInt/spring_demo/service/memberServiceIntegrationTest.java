package com.springInt.spring_demo.service;

import com.springInt.spring_demo.domain.Member;
import com.springInt.spring_demo.repository.MemberRepository;
import com.springInt.spring_demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

/**
 * @SpringBootTest: Runs the Spring container alongside the test.
 * @Transactional: If this annotation is applied to a test case, a transaction is started before the test begins
 *                  , and it is always rolled back after the test completes. This ensures that no data remains in the database, preventing interference with subsequent tests.
 */

@Transactional
class memberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("TestUser");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicatedException(){
        //given
        Member member1 = new Member();
        member1.setName("TestUser1");

        Member member2 = new Member();
        member2.setName("TestUser1");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }
}