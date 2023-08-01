package com.example.diary.service;

import com.example.diary.dto.DiaryDto;
import com.example.diary.entity.Diary;
import com.example.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public void save(DiaryDto diaryDto) {
        Diary diary = Diary.toSaveEntity(diaryDto);
        diaryRepository.save(diary);
    }

    public List<DiaryDto> findAll() {
        List<Diary> diaryList = diaryRepository.findAll();
        List<DiaryDto> diaryDtoList = new ArrayList<>();
        for (Diary diary: diaryList){
            diaryDtoList.add(DiaryDto.toDiaryDto(diary));
        }
        return diaryDtoList;
    }

    public DiaryDto findById(Long id){
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        if (optionalDiary.isPresent()){
            Diary diary = optionalDiary.get();
            DiaryDto diaryDto = DiaryDto.toDiaryDto(diary);
            return diaryDto;
        }else{
            return null;
        }
    }

    public DiaryDto update(DiaryDto diaryDto){
        Diary diary = Diary.toUpdateEntity(diaryDto);
        diaryRepository.save(diary);
        return findById(diaryDto.getId());
    }

    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }
}
