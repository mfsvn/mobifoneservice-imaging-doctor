package com.tunv.ham10000.repo;

import com.tunv.ham10000.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findAllByUsername(String username);
    Users findAllByUsernameAndPassword(String username, String password);

}
