package com.days.momentb.personalboard.service;

import com.days.momentb.common.dto.PageRequestDTO;
import com.days.momentb.common.dto.PageResponseDTO;
import com.days.momentb.personalboard.dto.PersonalBoardDTO;
import com.days.momentb.personalboard.entity.PersonalBoard;
import com.days.momentb.personalboard.entity.PersonalBoardLocation;
import com.days.momentb.personalboard.repository.PersonalBoardRepository;
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
public class PersonalBoardServiceImpl implements PersonalBoardService{


    private final ModelMapper modelMapper;
    private final PersonalBoardRepository personalBoardRepository;

    @Override
    public Long register(PersonalBoardDTO personalBoardDTO) {
        //1.dto->vo
        log.info(personalBoardDTO.getLocations());
        log.info(personalBoardDTO.getPictures());

        PersonalBoard personalBoard = modelMapper.map(personalBoardDTO, PersonalBoard.class);



        //set->list변경필요
//               PersonalBoard personalBoard = PersonalBoard.builder().
//        pbNo(personalBoardDTO.getPbNo())
//               .pbContent(personalBoardDTO.getPbContent())
//               .pbRegDate(personalBoardDTO.getPbRegDate())
//               .pbModDate(personalBoardDTO.getPbModDate())
//               .memId(personalBoardDTO.getMemId())
//               .tags(personalBoardDTO.getTags())
//               .locations(personalBoardDTO.getLocations())
//               .pictures(personalBoardDTO.getPictures())
//        .build();


        log.info(personalBoard.getLocations());
        log.info(personalBoard.getPictures());
        //2.insert
        personalBoardRepository.save(personalBoard);

        return personalBoard.getPbNo();
    }

    @Override
    public PageResponseDTO<PersonalBoardDTO> getList(PageRequestDTO pageRequestDTO) {

        char[] typeArr = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("pbNo").descending());

        Page<PersonalBoard> result = personalBoardRepository.search1(typeArr, keyword, pageable);

        List<PersonalBoardDTO> dtoList = result.get().map(
                personalBoard -> modelMapper.map(personalBoard, PersonalBoardDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return new PageResponseDTO<>(pageRequestDTO, (int)totalCount, dtoList);

    }

    @Override
    public PersonalBoardDTO read(Long pbNo) {
        Optional<PersonalBoard> result = personalBoardRepository.findById(pbNo);

        if(result.isEmpty()){
            throw new RuntimeException("NOT FOUND");
        }

        return modelMapper.map(result.get(), PersonalBoardDTO.class);

    }

    @Override
    public void modify(PersonalBoardDTO personalBoardDTO) {

        Optional<PersonalBoard> result = personalBoardRepository.findById(personalBoardDTO.getPbNo());

        if(result.isEmpty()){
            throw new RuntimeException("NOT FOUND");
        }

        PersonalBoard personalBoard = result.get();
        personalBoard.change(personalBoardDTO.getPbContent());

        personalBoardRepository.save(personalBoard);


    }

    @Override
    public void delete(Long pbNo) {

        personalBoardRepository.deleteById(pbNo);
    }
}
