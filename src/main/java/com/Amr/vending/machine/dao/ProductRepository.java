package com.Amr.vending.machine.dao;

import com.Amr.vending.machine.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
