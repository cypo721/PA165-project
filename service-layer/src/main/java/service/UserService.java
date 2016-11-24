package service;

import entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

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
    User createUser(User usr, String password);
    
    void changePassword(Long id, String newPassword);
    
    boolean authenticate(Long id, String password);
    
    boolean isEmployee(Long id);
    
    User getUserById(Long id);
    
    User getUserByEmail(String email);
    
    public List<User> getAllUsers();
}
