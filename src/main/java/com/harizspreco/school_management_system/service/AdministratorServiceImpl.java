package com.harizspreco.school_management_system.service;

import com.harizspreco.school_management_system.entity.Administrator;
import com.harizspreco.school_management_system.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService{
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public void save(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    @Override
    public long count() {
        return administratorRepository.count();
    }
}
