package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.ReplyDTO;
import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Reply;

import java.util.List;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    List<ReplyDTO> getList(Long gno);
    void modify(ReplyDTO dto);
    void remove(Long rno);


    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }

    default Reply dtoToEntity(ReplyDTO dto){
        Guestbook guestbook = Guestbook.builder()
                .gno(dto.getGno())
                .build();
        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .guestbook(guestbook)
                .build();
        return reply;
    }
}
