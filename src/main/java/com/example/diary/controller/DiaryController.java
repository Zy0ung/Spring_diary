package com.example.diary.controller;

import com.example.diary.dto.DiaryDto;
import com.example.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/save")
    public String saveForm() {return "save";}

    @PostMapping("/save") // 일기 저장
    public String saveForm(@ModelAttribute DiaryDto diaryDto) {
        System.out.printf("Dto = " + diaryDto);
        diaryService.save(diaryDto);
        return "index";
    }

    @GetMapping("/update/{id}") // 일기 업데이트
    public String updateForm() {
        return "update";
    }
}
