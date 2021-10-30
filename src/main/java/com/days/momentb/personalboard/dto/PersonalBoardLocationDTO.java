package com.days.momentb.personalboard.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalBoardLocationDTO {

    private String pName;

    private String pAddress;

    private String pLat;

    private String pLng;

}

