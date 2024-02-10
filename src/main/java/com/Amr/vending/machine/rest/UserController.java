package com.Amr.vending.machine.rest;

import com.Amr.vending.machine.entity.User;
import com.Amr.vending.machine.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    public UserController(UserService theUserService){
        this.userService=theUserService;
    }
    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }
    //add mapping for GET /employees/{employeeId}
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId){
        User theUser= userService.findById(userId);
        if(theUser==null){
            throw new RuntimeException("User id not found - "+userId);
        }
        return theUser;
    }
    //add mapping for POST /users - add new user
    @PostMapping("/users")
    public User addUser(@RequestBody User theUser){

        theUser.setId(0);
        User dbUser=userService.save(theUser);
        return dbUser;
    }
    //add mapping for PUT /users -update existing user
    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser){
        User dbUser=userService.save(theUser);
        return dbUser;
    }
    //add mapping for DELETE /users/{userId}
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId){
        User tmpUser= userService.findById(userId);
        //throw exception if null
        if(tmpUser==null){
            throw new RuntimeException("User id not found - "+userId);
        }
        userService.deleteById(userId);
        return "Deleted user - " + userId;
    }
    @PostMapping("/buyers/{userId}/buyProduct")
    public void buyProduct(@PathVariable int userId, @RequestParam int coins){
        userService.buyProduct(userId,coins);
    }   
    @PostMapping("/buyers/{userId}/resetDeposit")
    public void resetDeposit(@PathVariable int userId){
        userService.resetDeposit(userId);
    }
    @PostMapping("/buyers/{userId}/addProduct")
    public void addProduct(@PathVariable int userId, @RequestParam int productId){
        userService.userAddProduct(productId,userId);
        
    }
    @PostMapping("/buyers/{userId}/addRole")
        public void addRole(@PathVariable int userId, @RequestParam int roleId){
            userService.userAddRole(roleId,userId);
        }    


}
