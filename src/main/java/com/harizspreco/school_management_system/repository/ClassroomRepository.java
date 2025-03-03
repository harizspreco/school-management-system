package com.harizspreco.school_management_system.repository;

import com.harizspreco.school_management_system.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
}
