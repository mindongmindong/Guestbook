package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.dto.PageRequestDTO;
import com.mindong.guestbook.dto.PageResultDTO;
import com.mindong.guestbook.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    GuestbookDTO read(Long gno);
    void remove(Long gno);
    void modify(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
