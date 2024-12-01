package com.springInt.spring_demo;

import com.springInt.spring_demo.repository.MemberRepository;
import com.springInt.spring_demo.repository.MemoryMemberRepository;
import com.springInt.spring_demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Menually Add Bean and containers.
 */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
