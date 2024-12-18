package com.springInt.spring_demo;

import com.springInt.spring_demo.aop.TimeTraceAop;
import com.springInt.spring_demo.repository.*;
import com.springInt.spring_demo.service.MemberService;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

/**
 * Menually Add Bean and containers.
 */
@Configuration
public class SpringConfig {

    /**
     * Injection Database
     */
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    /**
     * Injection JAP
     */
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    /**
     *  Injection Spring JPA
     */
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Bean
//    public DataSource dataSource() {
//        Dotenv dotenv = Dotenv.configure().load();
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl(dotenv.get("MYSQL_URL"));
//        hikariDataSource.setUsername(dotenv.get("MYSQL_USERNAME"));
//        hikariDataSource.setPassword(dotenv.get("MYSQL_PASSWORD"));
//        hikariDataSource.setMaximumPoolSize(10);
//        hikariDataSource.setMinimumIdle(2);
//        hikariDataSource.setIdleTimeout(30000);
//        return hikariDataSource;
//    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource());
//        return new JdbcTemplateMemberRepository(dataSource());
//        return new JpaMemberRepository(em);
//    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
