package com.days.momentb.personalboard.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="pName")
public class PersonalBoardLocationDTO {

    private String pName;

    private String pAddress;

    private String pLat;

    private String pLng;

}

