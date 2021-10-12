package com.days.momentb.personalboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalBoardDTO {

    private Long bNum;

    private Long bPicCount;

    private String content;

    private LocalDateTime bRegDate;

    private LocalDateTime bModDate;

    private String memId;



}
