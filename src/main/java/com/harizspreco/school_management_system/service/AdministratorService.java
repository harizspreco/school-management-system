package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Administrator;

public interface AdministratorService {
    void save(Administrator administrator);
    long count();
}
