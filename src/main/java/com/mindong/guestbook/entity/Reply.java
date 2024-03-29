package com.mindong.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString(exclude = "guestbook")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guestbook guestbook;
}
