package service;

import entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Václav Zouzalík
 */
@Service
public interface UserService {
    
    /**
     * Cretae a user in the system
     * @param usr 
     * @param password
     * @return newly created user entity
     */
    User createUser(User usr, String password);
    
    /**
     * Change password of the given user
     * @param id of the user
     * @param newPassword 
     */
    void changePassword(Long id, String newPassword);
    
    /**
     * Authenticate user in the system
     * @param id
     * @param password
     * @return true if the authentication was successfull
     */
    boolean authenticate(Long id, String password);
    
    /**
     * Check wheter a user has employee status or not
     * @param id
     * @return true when the user has employee status
     */
    boolean isEmployee(Long id);
    
    /**
     * Find user by id
     * @param id
     * @return found user or null
     */
    User getUserById(Long id);
    
    /**
     * Find user by email
     * @param email
     * @return found user or null
     */
    User getUserByEmail(String email);
    
    /**
     * Get all users
     * @return 
     */
    public List<User> getAllUsers();

    /**
     * Deletes specified user
     */
    public void delete(User user);
}
