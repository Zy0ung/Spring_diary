package com.example.diary.dto;

// DTO(Data Transfer Object) 데이터를 전송할 때 사용하는 객체

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class DiaryDto {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
