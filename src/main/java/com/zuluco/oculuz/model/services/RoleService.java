package com.zuluco.oculuz.model.services;

import com.zuluco.oculuz.model.entities.Role;
import com.zuluco.oculuz.model.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findById(1L).isEmpty()) {
            Role role = new Role();
            role.setId(1L);
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        if (roleRepository.findById(2L).isEmpty()) {
            Role role = new Role();
            role.setId(2L);
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        if (roleRepository.findById(3L).isEmpty()) {
            Role role = new Role();
            role.setId(3L);
            role.setName("ROLE_AUTHOR");
            roleRepository.save(role);
        }
    }
}