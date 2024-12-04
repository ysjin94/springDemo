package com.springInt.spring_demo.repository;

import com.springInt.spring_demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository
{
    //JPQL SELECT M FROM MEMBER M WHERE M.NAME = ?
    @Override
    Optional<Member> findByName(String name);
}
