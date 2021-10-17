package com.days.momentb.miniboard.service;

import com.days.momentb.common.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    @Test
    public void testList(){
        Long mbNo = 198L;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .build();

        log.info(replyService.getListOfBoard(mbNo, pageRequestDTO));
    }

}
