package com.Amr.vending.machine.service;

import com.Amr.vending.machine.dao.ProductRepository;
import com.Amr.vending.machine.dao.UserRepository;
import com.Amr.vending.machine.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{
    ProductRepository productRepository;
        UserRepository userRepository;

    @Autowired
    public ProductServiceImp(ProductRepository theProductRepository,UserRepository theUserRepository){
        productRepository=theProductRepository;
        userRepository=theUserRepository;
    }
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product> result = productRepository.findById(theId);

        Product theProduct = null;

        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find Product id - " + theId);
        }

        return theProduct;
    }

    @Override
    public Product save(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }
    
    @Override
    public void buyProduct(int productId, int coins) {
        if (coins%5==0) {
            Product theProduct = findById(productId);
            if (theProduct.getAmountAvailable() > 0) {
                if (coins >= theProduct.getCost()) {
                    theProduct.setAmountAvailable(theProduct.getAmountAvailable() - 1);
                    save(theProduct);
                }
            }
        }else {
            throw new RuntimeException("should enter 5 or 10 ,50 ,100");
        }
    }

    @Override
    public void userAddProduct(int productId, int userId) {
        Product theProduct = findById(productId);
        theProduct.setUser(userRepository.findById(userId).get());
        save(theProduct);
    }
}
