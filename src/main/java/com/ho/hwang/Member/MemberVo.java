package com.ho.hwang.Member;


import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class MemberVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberNo;

    private String id;
    private String name;

    @Builder
    public MemberVo(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
