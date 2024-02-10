package com.Amr.vending.machine.service;

import com.Amr.vending.machine.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int theId);

    User save(User theUser);

    void deleteById(int theId);
    void buyProduct(int userId,int coins);
    void resetDeposit(int userId);
    void userAddProduct(int productId, int userId);
    void findByUsername(String username);
    
}
