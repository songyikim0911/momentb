package com.days.momentb.miniboard.service;

import com.days.momentb.common.dto.PageRequestDTO;
import com.days.momentb.common.dto.PageResponseDTO;
import com.days.momentb.miniboard.dto.MiniBoardDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MiniBoardService {


    Long register(MiniBoardDTO miniBoardDTO);

    PageResponseDTO<MiniBoardDTO> getList(PageRequestDTO pageRequestDTO);

    MiniBoardDTO read(Long bNum);

    void modify(MiniBoardDTO miniBoardDTO);

    void delete(Long bNum);

}
