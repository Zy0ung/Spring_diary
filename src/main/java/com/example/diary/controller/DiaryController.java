package com.example.diary.controller;

import com.example.diary.dto.DiaryDto;
import com.example.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/") // 일기 리스트
    public String findAll(Model model){
        // DB에서 전체 게시글 데이터를 가져와서 보여줌
        List<DiaryDto> diaryDtoList = diaryService.findAll();
        model.addAttribute("diaryList", diaryDtoList);
        return "list";
    }

    @GetMapping("/{id}") // 일기 디테일 조회
    public String findById(@PathVariable Long id, Model model) {
        DiaryDto diaryDto = diaryService.findById(id);
        model.addAttribute("diary", diaryDto);
        return "detail";
    }

    @GetMapping("/update/{id}") // 일기 수정 페이지
    public String updateForm(@PathVariable Long id, Model model){
        DiaryDto diaryDto = diaryService.findById(id);
        model.addAttribute("diaryUpdate", diaryDto);
        return "update";
    }

    @PostMapping("/update") // 일기 수정
    public String update(@ModelAttribute DiaryDto diaryDto, Model model) {
        DiaryDto diary = diaryService.update(diaryDto);
        model.addAttribute("diary", diary);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        diaryService.delete(id);
        return "redirect:/diary/";
    }
}
