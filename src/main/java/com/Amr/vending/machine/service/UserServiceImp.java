package com.Amr.vending.machine.service;

import com.Amr.vending.machine.dao.ProductRepository;
import com.Amr.vending.machine.dao.UserRepository;
import com.Amr.vending.machine.entity.Product;
import com.Amr.vending.machine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserServiceImp implements UserService {
    UserRepository userRepository;
    ProductRepository productRepository;
       
    @Autowired
    public UserServiceImp(UserRepository theUserRepository,ProductRepository theProductRepository){
        userRepository=theUserRepository;
        productRepository=theProductRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the user
            throw new RuntimeException("Did not find User id - " + theId);
        }

        return theUser;
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public void buyProduct(int userId,int coins) {
        if (coins%5==0) {
            User theUser = findById(userId);
            List<Product> products = productRepository.findAll();
            for (Product product : products) {
                if (product.getCost() <= coins) {
                    theUser.addProduct(product);
                    theUser.setDeposit(theUser.getDeposit() + coins - product.getCost());
                    userRepository.save(theUser);
                    return;
                }
            }
        }
    }
    public void resetDeposit(int userId){
        User theUser = findById(userId);
        theUser.setDeposit(0);
        userRepository.save(theUser);
    }
    public void userAddProduct(int productId, int userId){
        Product theProduct = productRepository.findById(productId).get();
        theProduct.setUser(userRepository.findById(userId).get());
        productRepository.save(theProduct);
    }
}
