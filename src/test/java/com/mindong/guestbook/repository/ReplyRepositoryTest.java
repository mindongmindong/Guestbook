package com.mindong.guestbook.repository;

import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Guestbook guestbook = guestbookRepository.findById(12L).get();
        Reply reply = Reply.builder()
                .text("asdasd")
                .guestbook(guestbook)
                .replyer("choimis")
                .build();
        replyRepository.save(reply);
    }
}
