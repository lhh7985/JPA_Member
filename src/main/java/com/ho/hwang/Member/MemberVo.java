package com.ho.hwang.Member;


import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class MemberVo {

    @Id
    private long memberNo;

    private String id;
    private String name;

    @Builder
    public MemberVo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberVo() {

    }
}
