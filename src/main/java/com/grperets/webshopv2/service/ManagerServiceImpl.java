package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager getById(Long id) {
        return managerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Manager manager) {
        managerRepository.save(manager);

    }

    @Override
    public void delete(Manager manager) {
        managerRepository.delete(manager);

    }

    @Override
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }
}
