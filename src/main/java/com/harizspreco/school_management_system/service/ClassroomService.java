package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Classroom;

import java.util.List;

public interface ClassroomService {
    List<Classroom> findAll();

    Classroom findById(int theId);

    void save(Classroom theClassroom);

    void deleteById(int theId);
}
