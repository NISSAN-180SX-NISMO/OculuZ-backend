package com.zuluco.oculuz.security.services;

import com.zuluco.oculuz.models.entities.RoleType;
import com.zuluco.oculuz.models.entities.Role;
import com.zuluco.oculuz.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleType.ROLE_USER));
        roles.add(new Role(RoleType.ROLE_ADMIN));
        roles.add(new Role(RoleType.ROLE_AUTHOR));

        for (Role role : roles) {
            if (!roleRepository.findByName(role.getName()).isPresent()) {
                roleRepository.save(role);
            }
        }
    }
}
