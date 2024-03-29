package com.mindong.guestbook.repository.search;

import com.mindong.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Pageable;

public interface SearchGuestbookRepository{
    Guestbook search1();
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
