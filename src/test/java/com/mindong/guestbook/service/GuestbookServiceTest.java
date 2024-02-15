package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.dto.PageRequestDTO;
import com.mindong.guestbook.dto.PageResultDTO;
import com.mindong.guestbook.entity.Guestbook;
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

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("Prev: " + resultDTO.isPrev());
        System.out.println("Next: " + resultDTO.isNext());
        System.out.println("Total: " + resultDTO.getTotalPage());
        System.out.println("-------------------------------------");
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        System.out.println("-------------------------------------");
        resultDTO.getPageList().forEach(i->System.out.println(i));
    }
    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("다")
                .build();
        PageResultDTO<GuestbookDTO,Guestbook> resultDTO = service.getList(pageRequestDTO);
        System.out.println("PREV: "+resultDTO.isPrev());
        System.out.println("NEXT: "+resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("-------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
