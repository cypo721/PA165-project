package service;

import dao.UserDao;
import entity.User;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
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
            usr.setPasswordHash(createHash(password));
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
        usr.setPasswordHash(createHash(newPassword));
    }
    
    @Override
    public boolean authenticate(Long id, String password)
    {
        if (password == null) throw new IllegalArgumentException("Password is null.");
        User usr = getUserById(id);
        return validatePassword(password, usr.getPasswordHash());
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
    
    private static String createHash(String password)
    {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
    {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static boolean validatePassword(String password, String correctHash)
    {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }
    
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }
    
    private static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
