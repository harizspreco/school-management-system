package com.harizspreco.school_management_system.controller;

import com.harizspreco.school_management_system.entity.Teacher;
import com.harizspreco.school_management_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Prikaz liste svih nastavnika
    @GetMapping
    public String listTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers/teachers";
    }

    // Prikaz detalja o nastavniku
    @GetMapping("/{id}")
    public String viewTeacher(@PathVariable Integer id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-details";
    }

    // Prikaz forme za dodavanje novog nastavnika
    @GetMapping("/new")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/teacher-form";
    }

    // Spremanje novog nastavnika
    @PostMapping
    public String saveTeacher(@ModelAttribute Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    // Prikaz forme za ažuriranje nastavnika
    @GetMapping("/{id}/edit")
    public String showEditTeacherForm(@PathVariable Integer id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-form";
    }

    // Ažuriranje nastavnika
    @PostMapping("/{id}")
    public String updateTeacher(@PathVariable Integer id, @ModelAttribute Teacher teacher) {
        teacher.setId(id); // Postavi ID za ažuriranje
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    // Brisanje nastavnika
    @GetMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}