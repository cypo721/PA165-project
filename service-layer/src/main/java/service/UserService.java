package service;

import entity.User;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author venca
 */
@Service
public interface UserService {
    User createUser(String givenName, String surname, String email, String password, String phone, String personType, String role);
    
    void changePassword(Long id, String newPassword);
    
    boolean authenticate(Long id, String password);
    
    boolean isEmployee(Long id);
}
