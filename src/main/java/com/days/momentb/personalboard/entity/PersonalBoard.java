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
    private Long bNum;

    private Long bPicCount;

    private String content;

    @CreationTimestamp
    private LocalDateTime bRegDate;

    @UpdateTimestamp
    private LocalDateTime bModDate;

    private String memId;

    public void change(String content){
        this.content = content;
    }



}
