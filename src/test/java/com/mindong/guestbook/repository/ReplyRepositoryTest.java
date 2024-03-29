package com.mindong.guestbook.repository;

import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertReply(){
        Guestbook guestbook = guestbookRepository.findById(4L).get();
        Reply reply = Reply.builder()
                .text("우우~")
                .guestbook(guestbook)
                .replyer("이민서")
                .build();
        replyRepository.save(reply);
    }
    @Test
    public void readReplyTest(){
        Guestbook guestbook = guestbookRepository.findById(4L).get();
        List<Reply> result = replyRepository.getRepliesByGuestbookOrderByRno(guestbook);
        result.stream().forEach(reply->{
            System.out.println(reply);
        });
    }
}
