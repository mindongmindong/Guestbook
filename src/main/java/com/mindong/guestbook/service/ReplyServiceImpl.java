package com.mindong.guestbook.service;

import com.mindong.guestbook.dto.ReplyDTO;
import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Reply;
import com.mindong.guestbook.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO dto){
        Reply reply = dtoToEntity(dto);
        replyRepository.save(reply);
        return reply.getRno();
    }
    @Override
    public List<ReplyDTO> getList(Long gno){
        List<Reply> result = replyRepository.getRepliesByGuestbookOrderByRno(Guestbook.builder().gno(gno).build());

        return result.stream().map(reply->
            entityToDTO(reply)
        ).collect(Collectors.toList());
    }
    @Override
    public void modify(ReplyDTO dto){
        Reply reply = dtoToEntity(dto);
        replyRepository.save(reply);
    }
    @Override
    public void remove(Long rno){
        replyRepository.deleteById(rno);
    }

}
