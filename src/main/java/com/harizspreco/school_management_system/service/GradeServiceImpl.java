package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Grade;
import com.harizspreco.school_management_system.entity.Student;
import com.harizspreco.school_management_system.entity.Subject;
import com.harizspreco.school_management_system.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public void addGrade(Student student, Subject subject, int gradeValue) {
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setGradeValue(gradeValue);
        gradeRepository.save(grade);
    }
}

