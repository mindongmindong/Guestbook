package com.mindong.guestbook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Member extends BaseEntity {
    @Id
    private String email;
    private String password;
    private String name;
}
