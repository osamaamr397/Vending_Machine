package com.Amr.vending.machine.service;

import com.Amr.vending.machine.dao.RoleRepository;
import com.Amr.vending.machine.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Amr.vending.machine.entity.User;
@RestController
@RequestMapping("/api/roles")
public class RoleServiceImp implements RoleService {
     UserRepository userRepository;
     RoleRepository roleRepository;
    @Autowired
    public RoleServiceImp(UserRepository theUserRepository,RoleRepository theRoleRepository){
        userRepository=theUserRepository;
        roleRepository=theRoleRepository;
    }
    
    @Override
    public void addRoleToUser(String username, String roleName) {
        User theUser = userRepository.findByUsername(username);
        if (theUser == null) {
            throw new RuntimeException("User not found - " + username);
        }
        roleRepository.addRoleToUser(theUser.getId(),roleName);
    }

    @Override
    public void addRole(String roleName) {
        roleRepository.addRole(roleName);
    }

    @Override
    public void deleteRole(String roleName) {
        roleRepository.deleteRole(roleName);
    }

    @Override
    public void deleteUserRole(String username, String roleName) {
        User theUser = userRepository.findByUsername(username);
        if (theUser == null) {
            throw new RuntimeException("User not found - " + username);
        }
        roleRepository.deleteUserRole(theUser.getId(),roleName);
    }

    @Override
    public void deleteAllRolesForUser(String username) {
        User theUser = userRepository.findByUsername(username);
        if (theUser == null) {
            throw new RuntimeException("User not found - " + username);
        }
        roleRepository.deleteAllRolesForUser(theUser.getId());
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAllRoles();
    }
    
}
