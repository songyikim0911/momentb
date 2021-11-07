package com.days.momentb.member.entity;


import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.Set;

@Entity//처음에 엔티티를 만들면 오류가 남 -> id가 없어서 그렇다. entity는 무조건 id를줘야함.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter//엔티티는 가능하면 불변, 게터
@ToString
public class Member {

    @Id
    private String mid;

    private String mpw;

    private String mname;


    @ElementCollection(fetch = FetchType.LAZY)
    private Set<com.days.momentb.member.entity.MemberRole> roleSet;

    public void changePassword(String password){
        this.mpw = password;
    }
}

