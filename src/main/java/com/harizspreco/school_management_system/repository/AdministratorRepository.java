package com.harizspreco.school_management_system.repository;

import com.harizspreco.school_management_system.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
