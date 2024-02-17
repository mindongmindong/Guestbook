package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.dto.PageRequestDTO;
import com.mindong.guestbook.dto.PageResultDTO;
import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Member;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    GuestbookDTO read(Long gno);
    void remove(Long gno);
    void modify(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Member member = Member.builder().email(dto.getWriter()).build();

        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return entity;
    }
    default GuestbookDTO entityToDto(Guestbook entity){
        Member member = entity.getWriter();
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
