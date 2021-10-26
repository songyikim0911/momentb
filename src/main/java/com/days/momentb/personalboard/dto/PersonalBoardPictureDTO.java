package com.days.momentb.personalboard.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="uuid")
public class PersonalBoardPictureDTO {

    private String uuid;

    private String fileName;

    private String savePath;

    private boolean selfDrawing;

    public String getLink(){
        return savePath+"/s_"+uuid+"_"+fileName;
    }




}
