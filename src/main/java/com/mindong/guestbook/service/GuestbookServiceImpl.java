package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.dto.PageRequestDTO;
import com.mindong.guestbook.dto.PageResultDTO;
import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Member;
import com.mindong.guestbook.entity.QGuestbook;
import com.mindong.guestbook.repository.GuestbookRepository;
import com.mindong.guestbook.repository.MemberRepository;
import com.mindong.guestbook.repository.ReplyRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository repository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(GuestbookDTO dto){
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();
        memberRepository.save(member);
        Guestbook guestbook = dtoToEntity(dto);
        repository.save(guestbook);
        return dto.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO,Object[]> getList(PageRequestDTO requestDTO){
        Function<Object[],GuestbookDTO> fn = (entity->
            entityToDto((Guestbook) entity[0],(Member)entity[1],(Long)entity[2])
        );
      //  Page<Object[]> result = repository.getGuestbookWithReplyCount(requestDTO.getPageable(Sort.by("gno").descending()));
        Page<Object[]> result = repository.searchPage(
                requestDTO.getType(),
                requestDTO.getKeyword(),
                requestDTO.getPageable(Sort.by("gno").descending())
        );
        return new PageResultDTO<>(result,fn);
    }

    @Override
    public GuestbookDTO get(Long gno){
        Object result = repository.getGuestbookByGno(gno);
        Object[] arr = (Object[]) result;
        return entityToDto((Guestbook) arr[0], (Member) arr[1], (Long) arr[2]);
    }
    @Transactional
    @Override
    public void removeWithReplies(Long gno){
        replyRepository.deleteBYGno(gno);
        repository.deleteById(gno);
    }
    @Override
    public void modify(GuestbookDTO dto){
        Optional<Guestbook> result = repository.findById(dto.getGno());
        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeContent(dto.getContent());
            guestbook.changeTitle(dto.getTitle());
            repository.save(guestbook);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qGuestbook.gno.gt(0L);

        booleanBuilder.and(expression);

        if(type == null || type.trim().length()==0){ // 검색조건이 없는 경우
            return booleanBuilder;
        }
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.writer.name.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
