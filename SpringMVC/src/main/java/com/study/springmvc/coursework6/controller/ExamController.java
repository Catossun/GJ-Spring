package com.study.springmvc.coursework6.controller;

import com.study.springmvc.coursework6.entity.Exam;
import com.study.springmvc.coursework6.entity.ExamPayStatus;
import com.study.springmvc.coursework6.entity.ExamSlot;
import com.study.springmvc.coursework6.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/coursework6/exam")
public class ExamController {
    @Autowired
    ExamService examService;

    private void prepareModel(Model model) {
        model.addAttribute("examPayStatus", ExamPayStatus.values());
        model.addAttribute("examSlots", ExamSlot.values());
        model.addAttribute("exams", examService.query());
        model.addAttribute("examSubjects", examService.queryExamSubjects());
    }

    @GetMapping("/")
    public String index(@ModelAttribute Exam exam, Model model) {
        prepareModel(model);
        model.addAttribute("_method", "POST");
        return "coursework6/exam";
    }

    @GetMapping("/{index}")
    public String query(@PathVariable int index, Model model) {
        prepareModel(model);
        Optional<Object> exam = examService.get(index);
        if (exam.isPresent()) {
            model.addAttribute("_method", "PUT");
            model.addAttribute("exam", exam);
            return "coursework6/exam";
        }
        return "redirect:./";
    }

    @PostMapping("/")
    public String add(Exam exam) {
        examService.add(exam);
        return "redirect:./";
    }

    @PutMapping("/{index}")
    public String update(@PathVariable int index, Exam exam) {
        examService.update(index, exam);
        return "redirect:./";
    }

    @DeleteMapping("/{index}")
    public String delete(@PathVariable int index) {
        examService.delete(index);
        return "redirect:./";
    }

    @PutMapping("/{index}/note")
    public String updateNote(@PathVariable int index, Exam exam) {
        examService.updateNote(index, exam.getExamNote());
        return "redirect:../";
    }
}
