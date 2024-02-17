package com.mindong.guestbook.repository;

import com.mindong.guestbook.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
