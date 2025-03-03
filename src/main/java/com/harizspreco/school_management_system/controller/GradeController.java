package com.harizspreco.school_management_system.controller;

import com.harizspreco.school_management_system.entity.Student;
import com.harizspreco.school_management_system.entity.Subject;
import com.harizspreco.school_management_system.service.GradeService;
import com.harizspreco.school_management_system.service.StudentService;
import com.harizspreco.school_management_system.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/add")
    public String addGrade(@RequestParam int studentId,
                           @RequestParam String subjectName,
                           @RequestParam int gradeValue) {

        Student student = studentService.findById(studentId);
        Subject subject = subjectService.findByName(subjectName);

        if (student != null && subject != null) {
            gradeService.addGrade(student, subject, gradeValue);
        }

        return "redirect:/students/" + studentId;
    }
}
