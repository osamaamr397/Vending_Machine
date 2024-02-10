package com.Amr.vending.machine.service;

public interface RoleService {
    void addRoleToUser(String username, String roleName);
    void addRole(String roleName);
    void deleteRole(String roleName);
    void deleteUserRole(String username, String roleName);
    void deleteAllRolesForUser(String username);
    void deleteAllRoles();
    
}
