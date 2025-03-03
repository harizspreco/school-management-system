package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.DTO.SubjectGradesDTO;
import com.harizspreco.school_management_system.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<SubjectGradesDTO> getSubjectsAndGrades(Integer studentId);
    void save(Student student);
    void deleteById(Integer studentId);
    Student findById(Integer studentId);
    Optional<Student> findByIdWithClassroom(Integer studentId);

}


