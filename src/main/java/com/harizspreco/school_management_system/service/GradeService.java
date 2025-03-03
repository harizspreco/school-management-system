package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Student;
import com.harizspreco.school_management_system.entity.Subject;

public interface GradeService {
    void addGrade(Student student, Subject subject, int gradeValue);
}
