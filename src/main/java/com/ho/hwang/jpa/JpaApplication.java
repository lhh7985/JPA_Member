package com.ho.hwang.jpa;

import com.ho.hwang.runner.MemberRunner;
import com.ho.hwang.runner.PgSQLRunner;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("com.ho.hwang.Member")
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public ApplicationRunner MemberRunner(){
        return new MemberRunner();
    }

//    Postgres Test
//    @Bean
//    public ApplicationRunner PgSQLRunner(){
//        return new PgSQLRunner();
//    }






}
