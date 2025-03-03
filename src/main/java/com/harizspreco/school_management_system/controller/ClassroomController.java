package com.harizspreco.school_management_system.controller;

import com.harizspreco.school_management_system.entity.Classroom;
import com.harizspreco.school_management_system.entity.Teacher;
import com.harizspreco.school_management_system.service.ClassroomService;
import com.harizspreco.school_management_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    // List all classrooms
    @GetMapping
    public String listClassrooms(Model model) {
        List<Classroom> classrooms = classroomService.findAll();
        model.addAttribute("classrooms", classrooms);
        return "classrooms/classrooms";
    }

    // Show classroom details
    @GetMapping("/{id}")
    public String viewClassroom(@PathVariable int id, Model model) {
        Classroom classroom = classroomService.findById(id);
        model.addAttribute("classroom", classroom);
        return "classrooms/classroom-details";
    }

    // Show add form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        Classroom newClassroom = new Classroom();
        newClassroom.setId(0);
        model.addAttribute("classroom", newClassroom);
        model.addAttribute("teachers", teacherService.findAll());
        return "classrooms/classroom-form";
    }

    // Save classroom
    @PostMapping
    public String saveClassroom(@ModelAttribute Classroom classroomForm,
                                @RequestParam(required = false) Integer teacherId) {
        // 1. Dohvati postojeći entitet iz baze ako ga ima
        Classroom existingClassroom = classroomService.findById(classroomForm.getId());
        if(existingClassroom == null){
            classroomService.save(classroomForm);
            return "redirect:/classrooms";
        }

        // 2. Ažuriraj polja
        existingClassroom.setName(classroomForm.getName());

        // 3. Postavi učitelja
        if (teacherId != null) {
            Teacher teacher = teacherService.findById(teacherId.intValue());
            existingClassroom.setTeacher(teacher);
        } else {
            existingClassroom.setTeacher(null);
        }

        // 4. Spremi ažurirani entitet
        classroomService.save(existingClassroom);

        return "redirect:/classrooms";
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Classroom classroom = classroomService.findById(id);
        model.addAttribute("classroom", classroom);
        model.addAttribute("teachers", teacherService.findAll());
        return "classrooms/classroom-form";
    }

    // Delete classroom
    @GetMapping("/{id}/delete")
    public String deleteClassroom(@PathVariable int id) {
        classroomService.deleteById(id);
        return "redirect:/classrooms";
    }
}