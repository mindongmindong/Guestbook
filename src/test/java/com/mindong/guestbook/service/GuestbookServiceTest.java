package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTest {
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("제목")
                .content("내용")
                .writer("작성자")
                .build();
        System.out.println(service.register(guestbookDTO));
    }
}
