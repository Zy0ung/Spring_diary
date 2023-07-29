package com.example.diary.service;

import com.example.diary.dto.DiaryDto;
import com.example.diary.entity.Diary;
import com.example.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public void save(DiaryDto diaryDto) {
        Diary diary = Diary.toSaveEntity(diaryDto);
        diaryRepository.save(diary);
    }
}
