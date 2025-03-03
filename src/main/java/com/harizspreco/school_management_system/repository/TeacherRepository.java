package com.harizspreco.school_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harizspreco.school_management_system.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
