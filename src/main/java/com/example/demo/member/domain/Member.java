package com.example.demo.member.domain;

import java.time.LocalDateTime;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private int mno;
    private String userid;
    private String passwd;
    private String name;
    private String email;
    private LocalDateTime regdate;

}