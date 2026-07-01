package com.wit.TickHere.repository;

import com.wit.TickHere.constant.ERole;
import com.wit.TickHere.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
