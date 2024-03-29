package com.mindong.guestbook.repository;

import com.mindong.guestbook.entity.Guestbook;
import com.mindong.guestbook.repository.search.SearchGuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestbookRepository extends JpaRepository<Guestbook,Long>, QuerydslPredicateExecutor<Guestbook>, SearchGuestbookRepository {
    @Query("select g,w from Guestbook g left join g.writer w where g.gno =:gno")
    Object getGuestbookWithWriter(@Param("gno") Long gno);

    //Memo 객체의 mno가 70이상 80이하를 구하고 역순으로 정렬
    //List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
    //Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable)
    //void deleteMemoByMnoLessThan(Long num);

    @Query("select g,r from Guestbook g left join Reply r on g= r.guestbook where g.gno =:gno")
    List<Object[]> getGuestbookwithReply(@Param("gno") Long gno);

    @Query(value="SELECT g,w,count(r) " +
            " FROM Guestbook g " +
            " LEFT JOIN g.writer w " +
            " LEFT JOIN Reply r ON g=r.guestbook " +
            " GROUP BY g")
            //countQuery = "SELECT count(g) FROM Guestbook g")
    Page<Object[]> getGuestbookWithReplyCount(Pageable pageable);

    @Query("SELECT g,w,count(r) " +
            " FROM Guestbook g " +
            " LEFT JOIN g.writer w" +
            " LEFT JOIN Reply r ON r.guestbook=g " +
            " WHERE g.gno = :gno")
    Object getGuestbookByGno(@Param("gno") Long gno);
}
