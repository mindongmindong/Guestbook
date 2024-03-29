package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.dto.PageRequestDTO;
import com.mindong.guestbook.dto.PageResultDTO;
import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Member;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    void modify(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Object[]> getList(PageRequestDTO requestDTO);

    GuestbookDTO get(Long gno);

    void removeWithReplies(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return entity;
    }
    default GuestbookDTO entityToDto(Guestbook guestbook,Member member, Long count){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(guestbook.getGno())
                .title(guestbook.getTitle())
                .content(guestbook.getContent())
                .regDate(guestbook.getRegDate())
                .modDate(guestbook.getModDate())
                .replyCount(count.intValue())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .build();
        return dto;
    }

}
