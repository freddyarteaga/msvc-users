package com.farteaga.arkam.msvc_users.repository;

import com.farteaga.arkam.msvc_users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // SELECT * FROM users WHERE email = <email>;
    List<User> findByFirstNameContainingIgnoreCase(String firstName);

    List<User> findAllByOrderByFirstNameAsc();


}