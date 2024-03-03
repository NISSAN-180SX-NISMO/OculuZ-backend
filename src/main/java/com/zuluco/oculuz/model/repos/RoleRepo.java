package com.zuluco.oculuz.model.repos;

import com.zuluco.oculuz.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
