package com.example.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // 로그인 시 사용자 아이디
    private String userpswd; // 로그인 시 사용자 비밀번호
    private String nickname; // 닉네임

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries;
}
