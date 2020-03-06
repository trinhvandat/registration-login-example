package com.spring.security.login.repository;

import com.spring.security.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find user by username in database
     */

    User findByUsername(String username );

}
