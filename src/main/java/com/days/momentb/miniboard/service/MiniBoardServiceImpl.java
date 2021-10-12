package com.days.momentb.miniboard.service;

import com.days.momentb.common.dto.PageRequestDTO;
import com.days.momentb.common.dto.PageResponseDTO;
import com.days.momentb.miniboard.dto.MiniBoardDTO;
import com.days.momentb.miniboard.entity.MiniBoard;
import com.days.momentb.miniboard.repository.MiniBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MiniBoardServiceImpl implements MiniBoardService {


    private final ModelMapper modelMapper;
    private final MiniBoardRepository miniBoardRepository;

    @Override
    public Long register(MiniBoardDTO miniBoardDTO) {
        //1.dto->vo
        MiniBoard miniBoard = modelMapper.map(miniBoardDTO, MiniBoard.class);
        //2.insert
        miniBoardRepository.save(miniBoard);
        return miniBoard.getMbNo();
    }

    @Override
    public PageResponseDTO<MiniBoardDTO> getList(PageRequestDTO pageRequestDTO) {

        char[] typeArr = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("mbNo").descending());

        Page<MiniBoard> result = miniBoardRepository.search1(typeArr, keyword, pageable);

        List<MiniBoardDTO> dtoList = result.get().map(
                        miniBoard -> modelMapper.map(miniBoard, MiniBoardDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return new PageResponseDTO<>(pageRequestDTO, (int)totalCount, dtoList);

    }

    @Override
    public MiniBoardDTO read(Long bNum) {
        Optional<MiniBoard> result = miniBoardRepository.findById(bNum);

        if(result.isEmpty()){
            throw new RuntimeException("NOT FOUND");
        }

        return modelMapper.map(result.get(), MiniBoardDTO.class);

    }

    @Override
    public void modify(MiniBoardDTO miniBoardDTO) {

        Optional<MiniBoard> result = miniBoardRepository.findById(miniBoardDTO.getMbNo());

        if(result.isEmpty()){
            throw new RuntimeException("NOT FOUND");
        }

        MiniBoard miniBoard = result.get();
        miniBoard.change(miniBoardDTO.getMbContent(), miniBoardDTO.getMbTitle());

        miniBoardRepository.save(miniBoard);


    }

    @Override
    public void delete(Long bNum) {

        miniBoardRepository.deleteById(bNum);
    }
}
