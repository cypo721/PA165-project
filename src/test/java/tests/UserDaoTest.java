/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import config.PersistenceSampleApplicationContext;
import dao.UserDao;
import entity.User;
import enums.PersonType;
import enums.Role;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author eduard
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserDao userDao;
    
    @Test
    public void testCreate(){
        User user1 = getUser();
        
        userDao.create(user1);
        Assert.assertNotNull(user1.getId());
        Assert.assertEquals(user1.getEmail(), "test.test@gmail.com");  
    }
    
    @Test
    public void testfindById(){
        User user1 = getUser();
        user1.setEmail("test1.test1@gmail.com");
        userDao.create(user1);
        Assert.assertEquals(user1.getGivenName(), userDao.findById(user1.getId()).getGivenName());
    }
    
    @Test
    public void testUpdate(){
        User user1 = getUser();
        
        userDao.create(user1);
        user1.setEmail("test2.test2@gmail.com");
        user1.setGivenName("Janko");
        userDao.update(user1);
        Assert.assertEquals(userDao.findById(user1.getId()).getGivenName(), "Janko");
    }
    
    @Test
    public void testcreateFindDelete(){
        
        User user1 = getUser();
        user1.setEmail("test3.test3@gmail.com");

        User user2 = getUser();
        user2.setEmail("test4.test4@gmail.com");
  
        userDao.create(user1);
        userDao.create(user2);
        
        Assert.assertEquals(userDao.findById(user1.getId()).getGivenName(), "test");

        Assert.assertEquals(userDao.findAll().size(), 2);
        userDao.delete(user1);
        Assert.assertEquals(userDao.findAll().size(), 1);
        userDao.delete(user2);
        Assert.assertEquals(userDao.findAll().size(), 0);
    }
    
    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void testNullEmail(){
        User user1 = getUser();
        user1.setEmail(null);
        
        userDao.create(user1);
    }
    
    @Test
    public void testFindAll() {
        Set<User> users = new HashSet<>();
       
        User user1 = getUser();
        user1.setEmail("test5.test5@gmail.com");

        User user2 = getUser();
        user2.setEmail("test6.test6@gmail.com");
  
        users.add(user1);
        users.add(user2);
        userDao.create(user1);
        userDao.create(user2);
        Set<User> result = new HashSet<>();
        result.addAll(userDao.findAll());
        assertEquals(result, users);
    }
    
    @Test
    public void testFindByEmail() {
        User user1 = getUser();
        user1.setEmail("test7.test7@gmail.com");

        userDao.create(user1);
        Assert.assertEquals(user1.getId(), userDao.findUserByEmail("test7.test7@gmail.com").getId());
    }
    
    @Test
    public void testNullDateOfJoined(){
        User user = getUser();
        user.setEmail("test8.test8@gmail.com");

        userDao.create(user);
        user.setJoinedDate(null);
        try{
            userDao.update(user);
            Assert.fail("Day of joined cannot be null");
        } catch(Exception e) {
            Assert.assertEquals("Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction", e.getMessage());
        }
    }
    
    @Test
    public void testEmailBadPattern(){
        User user = getUser();
        user.setEmail("test8.testgmail.com");
        try {
            userDao.create(user);
            Assert.fail("Good pattern of email");
        } catch(Exception e) {
            Assert.assertEquals("Validation failed for classes [entity.User] during persist time for groups [javax.validation.groups.Default, ]\n" +
            "List of constraint violations:[\n" +
            "	ConstraintViolationImpl{interpolatedMessage='must match \".+@.+\\....?\"', propertyPath=email, rootBeanClass=class entity.User, messageTemplate='{javax.validation.constraints.Pattern.message}'}\n" +
            "]", e.getMessage());
        }
    }
    
     private User getUser() {
       User user1 = new User();
        
        user1.setGivenName("test");
        user1.setSurname("test");
        user1.setEmail("test.test@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("test");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Calendar cal1 = Calendar.getInstance();
        user1.setJoinedDate(cal1.getTime());
        
        return user1;
    }
}
