package com.ho.hwang.runner;

import com.ho.hwang.Member.Member;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class MemberRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member memberVo = new Member();
        memberVo.setName("GyoungHui");
        memberVo.setId("lgh");
//        entityManager.persist(memberVo);

        //하이버네이트 session
        Session session = entityManager.unwrap(Session.class);
        session.save(memberVo);
    }
}
