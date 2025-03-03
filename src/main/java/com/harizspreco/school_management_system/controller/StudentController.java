package com.harizspreco.school_management_system.controller;

import com.harizspreco.school_management_system.DTO.SubjectGradesDTO;
import com.harizspreco.school_management_system.entity.Classroom;
import com.harizspreco.school_management_system.entity.Student;
import com.harizspreco.school_management_system.entity.Subject;
import com.harizspreco.school_management_system.entity.Grade;
import com.harizspreco.school_management_system.service.ClassroomService;
import com.harizspreco.school_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/students")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/add")
    public String addStudent(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam int classroomId) {
        Classroom classroom = classroomService.findById(classroomId);
        if (classroom != null) {
            studentService.save(new Student(firstName, lastName, classroom));
        }
        return "redirect:/classrooms/" + classroomId + "/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        int classroomId = student.getClassroom().getId();
        studentService.deleteById(id);
        return "redirect:/classrooms/" + classroomId + "/edit";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable int id, Model model) {
        // Dohvati studenta sa učitanim razredom
        Optional<Student> optionalStudent = studentService.findByIdWithClassroom(id);

        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id: " + id);
        }

        Student student = optionalStudent.get();
        Classroom classroom = student.getClassroom();
        List<Subject> subjects = student.getClassroom().getSubjects();
        if (subjects == null || subjects.isEmpty()) {
            throw new RuntimeException("No subjects found for the classroom");
        }

        // Grupiraj ocjene po predmetu
        Map<Subject, List<Integer>> gradesBySubject = student.getGrades().stream()
                .collect(Collectors.groupingBy(
                        Grade::getSubject,
                        Collectors.mapping(Grade::getGradeValue, Collectors.toList())
                ));

        // Pripremi DTO za prikaz
        List<SubjectGradesDTO> subjectGrades = subjects.stream()
                .map(subject -> new SubjectGradesDTO(
                        subject.getName(),
                        gradesBySubject.getOrDefault(subject, Collections.emptyList())
                ))
                .collect(Collectors.toList());

        model.addAttribute("student", student);
        model.addAttribute("subjectGrades", subjectGrades);
        return "students/student-details";
    }
}