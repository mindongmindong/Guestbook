package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.ReplyDTO;
import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Reply;
import com.mindong.guestbook.repository.GuestbookRepository;
import com.mindong.guestbook.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

//    @Test
//    public void entityToDtoTest(){
//        Guestbook guestbook = Guestbook.builder()
//                .gno()
//                .build();
//        Reply reply = Reply.builder()
//
//                .build();
//        ReplyDTO dto = replyService.entityToDTO(reply);
//        System.out.println(dto);
//    }
    @Test
    public void dtoToEntity(){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(4L)
                .text("ads")
                .replyer("midngd")
                .gno(40L)
                .build();
        Reply reply = replyService.dtoToEntity(dto);
        System.out.println(reply);
    }
    @Test
    public void registerTest(){
        ReplyDTO dto = ReplyDTO.builder()
                .text("ads")
                .replyer("midngd")
                .gno(4L)
                .build();
        replyService.register(dto);
    }
    @Test
    public void getListTest(){
        List<ReplyDTO> dto = replyService.getList(4L);
        dto.stream().forEach(list->{
            System.out.println(list);
        });
    }
    @Test
    public void modifyTest(){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(6L)
                .text("오오~")
                .replyer("밍서밍서")
                .gno(4L)
                .build();
        replyService.modify(dto);
    }
}
