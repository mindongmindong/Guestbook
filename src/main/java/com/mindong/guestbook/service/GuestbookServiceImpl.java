package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.GuestbookDTO;
import com.mindong.guestbook.entity.Guestbook;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{
    @Override
    public Long register(GuestbookDTO dto){
        log.info("Dto------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);
        log.info(entity);
        return null;
    }
}
