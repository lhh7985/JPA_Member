package com.ho.hwang.Member;


import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Member {

    @Id
    private long memberNo;

    private String id;
    private String username;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date created = new Date();

    @Embedded
    private Address address;


    @Builder
    public Member(String id, String username,String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Member() {

    }
}
