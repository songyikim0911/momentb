package com.days.momentb.miniboard.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude ="miniBoard")
public class Reply {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbReNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private MiniBoard miniBoard;

    private String mbReContent;

    private String mbReWriter;

    @CreationTimestamp
    private LocalDateTime mbReRegDate;

    @UpdateTimestamp
    private LocalDateTime mbReModDate;

    private Long originReNo;

    private Long reDepth;


}
