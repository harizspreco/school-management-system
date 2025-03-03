package com.harizspreco.school_management_system.controller;

import com.harizspreco.school_management_system.entity.Subject;
import com.harizspreco.school_management_system.entity.Teacher;
import com.harizspreco.school_management_system.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subjects")
@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String listSubjects(Model model){
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects",subjects);
        return "subjects/subjects";
    }

    @GetMapping("/new")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/subject-form";
    }

    @PostMapping
    public String saveTeacher(@ModelAttribute Subject subject) {
        subjectService.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("{id}/delete")
    public String deleteSubject(@PathVariable Integer id){
        subjectService.deleteById(id);
        return  "redirect:/subjects";
    }
}
