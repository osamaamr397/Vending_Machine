package com.Amr.vending.machine.service;

import com.Amr.vending.machine.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(int theId);

    Product save(Product theEmployee);

    void deleteById(int theId);

    void buyProduct(int productId, int coins);
    void userAddProduct(int productId, int userId);
    
}
