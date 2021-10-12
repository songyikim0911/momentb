package com.days.momentb.personalboard.service;

import com.days.momentb.personalboard.dto.PersonalBoardDTO;
import com.days.momentb.personalboard.repository.PersonalBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class PersonalBoardServiceTests {

    @Autowired
    private PersonalBoardService personalBoardService;

    @Test
    public void testModify(){
        PersonalBoardDTO personalBoardDTO = PersonalBoardDTO.builder().bNum(200L).content("gg")
                .build();

        personalBoardService.modify(personalBoardDTO);
    }

    @Test
    public void testRegister(){

        IntStream.rangeClosed(1,200).forEach(i->{
            PersonalBoardDTO personalBoardDTO = PersonalBoardDTO.builder()
                    .content("content..."+i)
                    .memId("user"+(i%10))
                    .build();

            Long bNum = personalBoardService.register(personalBoardDTO);
            log.info("bNum:" + bNum);
        });

    }

}
