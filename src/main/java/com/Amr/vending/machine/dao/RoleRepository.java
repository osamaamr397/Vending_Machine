package com.Amr.vending.machine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Amr.vending.machine.entity.Role;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    void addRoleToUser(int userId, String roleName);
    void addRole(String roleName);
    void deleteRole(String roleName);
    void deleteUserRole(int userId, String roleName);
    void deleteAllRolesForUser(int userId);
    void deleteAllRoles();
}
