package com.days.momentb.miniboard.repository;

import com.days.momentb.miniboard.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findReplyByMiniBoard_MbNoOrderByMbReNo(Long mbNo);
}
