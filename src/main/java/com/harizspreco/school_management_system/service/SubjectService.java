package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Subject;

import java.util.List;

public interface SubjectService{
    Subject findByName(String name);
    List<Subject> findAll();
    void save(Subject subject);
    void deleteById(Integer subjectId);
}
