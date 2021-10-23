package com.days.momentb.personalboard.repository;

import com.days.momentb.personalboard.dto.PersonalBoardDTO;
import com.days.momentb.personalboard.entity.PersonalBoard;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

@SpringBootTest
@Log4j2
public class PersonalBoardRepositoryTests {


    @Autowired
    private PersonalBoardRepository personalBoardRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void testSearch1(){
        char[] typeArr=null;
        String keyword=null;
        Pageable pageable = PageRequest.of(0,10, Sort.by("pbNo").descending());
        Page<PersonalBoard> result = personalBoardRepository.search1(typeArr, keyword, pageable);

        result.get().forEach(personalBoard ->
        {
            log.info(personalBoard);
            log.info("-----");

            PersonalBoardDTO personalBoardDTO = modelMapper.map(personalBoard, PersonalBoardDTO.class);

          log.info(personalBoardDTO);

        });

    }

}
