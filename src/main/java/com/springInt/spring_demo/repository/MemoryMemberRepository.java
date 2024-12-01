package com.springInt.spring_demo.repository;

import com.springInt.spring_demo.domain.Member;
import org.apache.catalina.Store;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long seqence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++seqence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return store.values().stream()
                .filter((member -> member.getName().equals(username)))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
