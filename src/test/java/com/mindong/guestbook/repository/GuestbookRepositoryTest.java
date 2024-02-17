package com.mindong.guestbook.repository;

import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Member;
import com.mindong.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

//    @Test
//    public void insertDummies(){
//        IntStream.rangeClosed(1,20).forEach(i->{
//
//            Guestbook guestbook = Guestbook.builder()
//                    .title("제목" + i)
//                    .content("내용"+i)
//                    .writer("작성자"+(i % 10))
//                    .build();
//            System.out.println(guestbookRepository.save(guestbook));
//        });
//    }
//    @Test
//    public void updateTest(){
//        Optional<Guestbook> result = guestbookRepository.findById(1L);
//
//        if(result.isPresent()){
//            Guestbook guestbook = result.get();
//            guestbook.changeTitle("이권민 폭로영상");
//            guestbook.changeWriter("최현수1");
//            guestbook.changeContent("이권민");
//
//            guestbookRepository.save(guestbook);
//        }
//    }

//    @Test
//    public void testQuery1(){
//        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
//
//        QGuestbook qGuestbook = QGuestbook.guestbook;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression expression = qGuestbook.title.contains(keyword);
//        builder.and(expression);
//        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
//        result.stream().forEach(guestbook -> {
//            System.out.println(guestbook);
//        });
//    }
//    @Test
//    public void testQuery2(){
//        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());
//        QGuestbook qGuestbook = QGuestbook.guestbook;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
//        BooleanExpression exContent = qGuestbook.content.contains(keyword);
//        BooleanExpression exAll = exTitle.or(exContent);
//        builder.and(exAll);
//        builder.and(qGuestbook.gno.gt(250L));
//        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
//        result.stream().forEach(guestbook -> {
//            System.out.println(guestbook);
//        });
//    }
    @Test
    public void insertGuestbook(){
        IntStream.rangeClosed(1,10).forEach(i->{
            Member member = Member.builder().email("choi"+i+"@ajou.ac.kr").build();
            Guestbook guestbook = Guestbook.builder()
                    .title("최현수이놈"+i)
                    .content("흐헤헤")
                    .writer(member)
                    .build();
            guestbookRepository.save(guestbook);
        });
    }
    @Test
    @Transactional
    public void testRead1(){
        Optional<Guestbook> result = guestbookRepository.findById(4L);
        Guestbook guestbook = result.get();

        System.out.println(guestbook);
        System.out.println(guestbook.getWriter());
    }
    @Test
    public void testReadWithWriter(){
        Object result = guestbookRepository.getGuestbookWithWriter(4L);

        Object[] arr = (Object[])result;

        System.out.println("---------");
        System.out.println(Arrays.toString(arr));
    }
    @Test
    public void testReadWithReply(){
        List<Object[]> result = guestbookRepository.getGuestbookwithReply(12L);
        System.out.println("---------");
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }
    @Test
    public void testReadReplyCount(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());

        Page<Object[]> result = guestbookRepository.getGuestbookWithReplyCount(pageable);

        result.get().forEach(row ->{
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }
    @Test
    public void testRead(){
        Object result = guestbookRepository.getGuestbookByBno(12L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }
}
