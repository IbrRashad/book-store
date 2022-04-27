package com.rashad.bookstore.repository;

import com.rashad.bookstore.entity.ERole;
import com.rashad.bookstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
    Boolean existsByName(ERole name);
}
