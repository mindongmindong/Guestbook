package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.dto.PageRequestDTO;
import com.mindong.guestbook.dto.PageResultDTO;
import com.mindong.guestbook.entity.Guestbook;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTest {
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO dto = GuestbookDTO.builder()
                .title("최현수 공부해라")
                .content("잠그만자라")
                .writerEmail("choi1@ajou.ac.kr")
                .build();
        Long num = service.register(dto);
        System.out.println(num);
    }

//    @Test
//    public void testList(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .build();
//        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//
//        System.out.println("Prev: " + resultDTO.isPrev());
//        System.out.println("Next: " + resultDTO.isNext());
//        System.out.println("Total: " + resultDTO.getTotalPage());
//        System.out.println("-------------------------------------");
//        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
//            System.out.println(guestbookDTO);
//        }
//        System.out.println("-------------------------------------");
//        resultDTO.getPageList().forEach(i->System.out.println(i));
//    }
//    @Test
//    public void testSearch(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .type("tc")
//                .keyword("다")
//                .build();
//        PageResultDTO<GuestbookDTO,Guestbook> resultDTO = service.getList(pageRequestDTO);
//        System.out.println("PREV: "+resultDTO.isPrev());
//        System.out.println("NEXT: "+resultDTO.isNext());
//        System.out.println("TOTAL: " + resultDTO.getTotalPage());
//
//        System.out.println("-------------------------------------");
//        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
//            System.out.println(guestbookDTO);
//        }
//
//        System.out.println("========================================");
//        resultDTO.getPageList().forEach(i -> System.out.println(i));
//    }
    @Test
    public void testGetList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<GuestbookDTO,Object[]> result = service.getList(pageRequestDTO);
        for(GuestbookDTO guestbookDTO : result.getDtoList()){
            System.out.println(guestbookDTO);
        }
    }
    @Test
    public void testGet(){
        GuestbookDTO guestbookDTO = service.get(12L);
        System.out.println(guestbookDTO);
    }
    @Test
    public void testRemove(){
        service.removeWithReplies(12L);
    }
    @Test
    public void testModify(){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(4L)
                .title("죽어랏 이권민")
                .content("죽어랏 이헌우")
                .build();
        service.modify(dto);
    }
}
