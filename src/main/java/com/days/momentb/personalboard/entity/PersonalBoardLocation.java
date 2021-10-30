package com.days.momentb.personalboard.entity;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PersonalBoardLocation {


    private String pName;

    private String pAddress;

    private String pLat;

    private String pLng;




}

