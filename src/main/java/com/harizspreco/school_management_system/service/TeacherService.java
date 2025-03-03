package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();

    Teacher findById(int theId);

    void save(Teacher theTeacher);

    void deleteById(int theId);
}
