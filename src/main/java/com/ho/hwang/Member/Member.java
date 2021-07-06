package com.ho.hwang.Member;


import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Member {

    @Id
    private long memberNo;

    private String id;
    private String name;

    @Builder
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member() {

    }
}
