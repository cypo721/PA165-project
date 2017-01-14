package service;

import dao.UserDao;
import entity.User;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Service;

import security.PasswordEncryption;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    
    @Override
    public User createUser(User usr, String password)
    {
        if (usr == null) throw new IllegalArgumentException("User is null.");
        if (password == null) throw new IllegalArgumentException("Password is null.");
        try
        {
            usr.setPasswordHash(PasswordEncryption.createHash(password));
            userdao.create(usr);
        }
        catch(Exception e)
        {
            throw new TransientDataAccessResourceException("Creating user failed", e);
        }
        
        return usr;
    }
    
    @Override
    public void changePassword(Long id, String newPassword)
    {
        if (newPassword == null) throw new IllegalArgumentException("Password is null.");
        User usr = getUserById(id);
        usr.setPasswordHash(PasswordEncryption.createHash(newPassword));
    }
    
    @Override
    public boolean authenticate(Long id, String password)
    {
        if (password == null) throw new IllegalArgumentException("Password is null.");
        User usr = getUserById(id);
        return PasswordEncryption.validatePassword(password, usr.getPasswordHash());
    }
    
    @Override
    public boolean isEmployee(Long id)
    {
        User usr = getUserById(id);
        return usr.getRole() == Role.EMPLOYEE;
    }
    
    @Override
    public User getUserById(Long id)
    {
        if (id == null) throw new IllegalArgumentException("Id is null");
        try
        {
            return userdao.findById(id);
        }
        catch(Exception e)
        {
            throw new TransientDataAccessResourceException("Finding of a user by id failed", e);
        }
        
    }
    
    @Override
    public User getUserByEmail(String email)
    {
        if (email == null) throw new IllegalArgumentException("Email is null.");
        try
        {
            return userdao.findUserByEmail(email);
        }
        catch(Exception e)
        {
            throw new TransientDataAccessResourceException("Finding of a user by email failed", e);
        }
        
    }
    
    @Override
    public List<User> getAllUsers() {
        try
        {
            return userdao.findAll();
        }
        catch(Exception e)
        {
            throw new TransientDataAccessResourceException("Finding of all users failed", e);
        }
    }

    @Override
    public void delete(User user){
        userdao.delete(user);
    }
}
