package com.days.momentb.personalboard.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PersonalBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pbNo;

    private String pbContent;

    @CreationTimestamp
    private LocalDateTime pbRegDate;

    @UpdateTimestamp
    private LocalDateTime pbModDate;

    private String memId;

    public void change(String content){
        this.pbContent = pbContent;
    }



}
