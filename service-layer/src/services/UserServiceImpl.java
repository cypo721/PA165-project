package service;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import enums.*;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao ud;
    
    @Override
    public User createUser(String givenName, String surname, String email, String password, String phone, String personType, String role)
    {
        User usr = new User();
        usr.setGivenName(givenName);
        usr.setSurname(surname);
        usr.setEmail(email);
        
        MessageDigest md;
        byte [] digest;
        try {
             md = MessageDigest.getInstance("SHA-512");
             digest = md.digest(password.getBytes());
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        usr.setPasswordHash(byteArrayToHexString(digest));
        
        usr.setPhone(phone);
        
        if(personType.trim().equalsIgnoreCase("legal"))
        {
            usr.setPersonType(PersonType.LEGAL);
        }
        if(personType.trim().equalsIgnoreCase("natural"))
        {
            usr.setPersonType(PersonType.NATURAL);
        }
        
        if(role.trim().equalsIgnoreCase("employee"))
        {
            usr.setRole(Role.EMPLOYEE);
        }
        if(role.trim().equalsIgnoreCase("customer"))
        {
            usr.setRole(Role.CUSTOMER);
        }
        ud.create(usr);
        
        return usr;
    }
    
    @Override
    public void changePassword(Long id, String newPassword)
    {
        User usr = ud.findById(id);
        
        MessageDigest md;
        byte [] digest;
        try {
             md = MessageDigest.getInstance("SHA-512");
             digest = md.digest(newPassword.getBytes());
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        usr.setPasswordHash(byteArrayToHexString(digest));
    }
    
    private static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
            Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
        return result;
    }
}
