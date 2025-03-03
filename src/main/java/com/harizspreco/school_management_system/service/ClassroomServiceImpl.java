package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Classroom;
import com.harizspreco.school_management_system.entity.Teacher;
import com.harizspreco.school_management_system.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService{

    private ClassroomRepository classroomRepository;

    @Autowired
    ClassroomServiceImpl(ClassroomRepository theClassroomRepository){ classroomRepository = theClassroomRepository; }


    @Override
    public List<Classroom> findAll() {
       return classroomRepository.findAll();
    }

    @Override
    public Classroom findById(int theId) {
        Optional<Classroom> result = classroomRepository.findById(theId);

        Classroom classroom = null;

        if(result.isPresent()){
            classroom = result.get();
        }else{
            throw new RuntimeException("Classroom with id " + theId + " not found.");
        }

        return classroom;
    }

    @Override
    public void save(Classroom classroom) {
        classroomRepository.saveAndFlush(classroom);
    }

    @Override
    public void deleteById(int theId) {
        Optional<Classroom> result = classroomRepository.findById(theId);

        Classroom theClassroom = null;

        if(result.isPresent()){
            theClassroom = result.get();
            theClassroom.setTeacher(null);
            classroomRepository.deleteById(theId);
        }else{
            throw new RuntimeException("Classroom does not exist!");
        }
    }
}
