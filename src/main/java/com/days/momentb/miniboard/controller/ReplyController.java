package com.days.momentb.miniboard.controller;

import com.days.momentb.common.dto.PageRequestDTO;
import com.days.momentb.common.dto.PageResponseDTO;
import com.days.momentb.miniboard.dto.ReplyDTO;
import com.days.momentb.miniboard.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/list/{mbno}")
    public PageResponseDTO<ReplyDTO> getListOfBoard(@PathVariable("mbno") Long mbNo, PageRequestDTO pageRequestDTO){

        return replyService.getListOfBoard(mbNo, pageRequestDTO);

    }

}
