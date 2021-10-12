package com.days.momentb.miniboard.dto;

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
public class MiniBoardDTO {

    private Long mbNo;

    private String mbContent;

    private String mbWriter;

    private LocalDateTime mbRegDate;

    private LocalDateTime mbModDate;

    private Long mbReCount;

    private String mbTitle;



}
