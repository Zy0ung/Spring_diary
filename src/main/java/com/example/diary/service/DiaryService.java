package com.example.diary.service;

import com.example.diary.dto.DiaryDto;
import com.example.diary.entity.Diary;
import com.example.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<DiaryDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작이기 때문에 1을 빼줌
        int pageLimit = 3; // 한 페이지에 보여줄 게시글 갯수
        // 한 페이지당 3개씩 글을 보여주기, id 기준으로 내림차순
        Page<Diary> diaryEntities =
            diaryRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        //.getContent()); // 요청 페이지에 해당하는 글
        //.getTotalElements()); // 전체 글갯수
        //.getNumber()); // DB로 요청한 페이지 번호
        //.getTotalPages()); // 전체 페이지 갯수
        //.getSize()); // 한 페이지에 보여지는 글 갯수
        //.hasPrevious()); // 이전 페이지 존재 여부
        //.isFirst()); // 첫 페이지 여부
        //.isLast()); // 마지막 페이지 여부

        // 목록 id, title, createTime
        Page<DiaryDto> diaryDtos = diaryEntities.map(diary -> new DiaryDto(diary.getId(), diary.getTitle(), diary.getCreateTime()));
        return diaryDtos;
    }
}
