package com.days.momentb.miniboard.repository;

import com.days.momentb.miniboard.entity.MiniBoard;
import com.days.momentb.miniboard.entity.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insert200(){
        IntStream.rangeClosed(1,200).forEach(i->{
            Long mbNo = (long)(200 - (i%5));

            int replyCount = (i % 5);

            IntStream.rangeClosed(0,replyCount).forEach(j->{

                MiniBoard miniBoard = MiniBoard.builder().mbNo(mbNo).build();

                Reply reply = Reply.builder()
                        .mbReContent("Reply...")
                        .mbReWriter("mbReWriter...")
                        .miniBoard(miniBoard)
                        .build();
                replyRepository.save(reply);



            });

        });
    }


    @Test
    public void testBymbNo(){
        Long mbNo = 200L;
        List<Reply> replyList
                =replyRepository.findReplyByMiniBoard_MbNoOrderByMbReNo(mbNo);
        replyList.forEach(reply -> log.info(reply));
    }

}
