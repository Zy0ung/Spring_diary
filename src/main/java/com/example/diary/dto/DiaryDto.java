package com.example.diary.dto;

// DTO(Data Transfer Object) 데이터를 전송할 때 사용하는 객체

import com.example.diary.entity.Diary;
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

    public static DiaryDto toDiaryDto(Diary diary){
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setId(diary.getId());
        diaryDto.setTitle(diary.getTitle());
        diaryDto.setText(diary.getText());
        diaryDto.setCreateTime(diary.getCreateTime());
        diaryDto.setUpdateTime(diary.getUpdateTime());
        return diaryDto;
    }

    public DiaryDto(Long id, String title, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
    }
}
