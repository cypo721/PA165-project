package service;

import dao.UserDao;
import entity.User;
import enums.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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
    private UserDao userdao;
    
    @Override
    public User createUser(String givenName, String surname, String email, String password, String phone, String personType, String role)
    {
        User usr = new User();
        usr.setGivenName(givenName);
        usr.setSurname(surname);
        usr.setEmail(email);

        usr.setPasswordHash(createHash(password));

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
        userdao.create(usr);
        
        return usr;
    }
    
    @Override
    public void changePassword(Long id, String newPassword)
    {
        User usr = userdao.findById(id);
        usr.setPasswordHash(createHash(newPassword));
    }
    
    @Override
    public boolean authenticate(Long id, String password)
    {
        User usr = userdao.findById(id);
        return validatePassword(password, usr.getPasswordHash());
    }
    
    @Override
    public boolean isEmployee(Long id)
    {
        User usr = userdao.findById(id);
        return usr.getRole() == Role.EMPLOYEE;
    }
    
    @Override
    public User getUserById(Long id)
    {
        return userdao.findById(id);
    }
    
    @Override
    public User getUserByEmail(String email)
    {
        return userdao.findUserByEmail(email);
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
