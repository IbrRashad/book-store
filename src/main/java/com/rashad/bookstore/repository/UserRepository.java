package com.rashad.bookstore.repository;

import com.rashad.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);
    Optional<User> findById(int id);
    Boolean existsByUserName(String username);
}
