package com.zuluco.oculuz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zuluco.oculuz.models.entities.RoleType;
import com.zuluco.oculuz.models.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleType name);
}
