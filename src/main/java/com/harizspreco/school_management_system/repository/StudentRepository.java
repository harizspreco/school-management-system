package com.harizspreco.school_management_system.repository;

import com.harizspreco.school_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s JOIN FETCH s.classroom WHERE s.id = :studentId")
    Optional<Student> findByIdWithClassroom(@Param("studentId") Integer studentId);
}