package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.DTO.SubjectGradesDTO;
import com.harizspreco.school_management_system.entity.Grade;
import com.harizspreco.school_management_system.entity.Student;
import com.harizspreco.school_management_system.entity.Subject;
import com.harizspreco.school_management_system.repository.GradeRepository;
import com.harizspreco.school_management_system.repository.StudentRepository;
import com.harizspreco.school_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<SubjectGradesDTO> getSubjectsAndGrades(Integer studentId) {
        Student student = studentRepository.findByIdWithClassroom(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Grade> grades = gradeRepository.findGradesByStudentId(studentId);

        // Grupiraj ocjene po predmetu
        Map<Subject, List<Integer>> gradesBySubject = grades.stream()
                .collect(Collectors.groupingBy(
                        Grade::getSubject,
                        Collectors.mapping(Grade::getGradeValue, Collectors.toList())
                )); // Ispravno zatvorene zagrade

        // Dohvati predmete iz razreda
        List<Subject> subjects = student.getClassroom().getSubjects();

        return subjects.stream()
                .map(subject -> new SubjectGradesDTO(
                        subject.getName(),
                        gradesBySubject.getOrDefault(subject, Collections.emptyList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student findById(Integer studentId){
        Optional<Student> result = studentRepository.findById(studentId);
        Student student = null;
        if(result.isPresent()){
            student = result.get();
            return student;
        }else {
            throw new RuntimeException("Student not found!");
        }
    }

    @Override
    public Optional<Student> findByIdWithClassroom(Integer studentId) {
        return studentRepository.findByIdWithClassroom(studentId);
    }
}