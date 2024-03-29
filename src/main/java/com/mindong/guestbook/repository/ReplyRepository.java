package com.mindong.guestbook.repository;

import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Modifying
    @Query("delete from Reply r where r.guestbook.gno = :gno")
    void deleteBYGno(Long gno);

    List<Reply> getRepliesByGuestbookOrderByRno(Guestbook guestbook);
}
