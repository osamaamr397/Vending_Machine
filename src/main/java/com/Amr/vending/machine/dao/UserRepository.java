package com.Amr.vending.machine.dao;

import com.Amr.vending.machine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
