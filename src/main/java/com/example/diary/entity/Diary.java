package com.example.diary.entity;
import com.example.diary.dto.DiaryDto;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
// DB에 테이블 역할을 하는 클래스
public class Diary extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static Diary toSaveEntity(DiaryDto diaryDto){
        Diary diary = new Diary();
        diary.setTitle(diaryDto.getTitle());
        diary.setText(diaryDto.getText());
        return diary;
    }
}

