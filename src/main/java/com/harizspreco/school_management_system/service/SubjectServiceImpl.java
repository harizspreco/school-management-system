package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Subject;
import com.harizspreco.school_management_system.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    private SubjectRepository subjectRepository;

    @Autowired
    SubjectServiceImpl(SubjectRepository theSubjectRepository){
        subjectRepository = theSubjectRepository;
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name).orElse(null);
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void deleteById(Integer subjectId) {
        subjectRepository.deleteById(subjectId);
    }

}

