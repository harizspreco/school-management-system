package com.harizspreco.school_management_system.repository;

import com.harizspreco.school_management_system.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query("SELECT g FROM Grade g JOIN FETCH g.subject WHERE g.student.id = :studentId")
    List<Grade> findGradesByStudentId(@Param("studentId") Integer studentId);
}