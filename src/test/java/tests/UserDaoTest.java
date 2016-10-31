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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserDao userDao;
    
    @Test
    public void testCreate(){
        User user1 = new User();
        
        user1.setGivenName("Michal");
        user1.setSurname("Skaredy");
        user1.setEmail("michal.skaredy@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("asda123");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Date joinedDate = new Date();
        user1.setJoinedDate(joinedDate);
        
        userDao.create(user1);
        Assert.assertNotNull(user1.getId());
        userDao.delete(user1);
        
        User user2 = new User();
        
        user2.setGivenName("Jozef");
        user2.setSurname("Hnusny");
        user2.setEmail("jozef.hnusny@gmail.com");
        user2.setPhone("0918732436");
        user2.setPasswordHash("asder");
        user2.setPersonType(PersonType.NATURAL);
        user2.setRole(Role.CUSTOMER);        
        
        joinedDate = new Date();
        user2.setJoinedDate(joinedDate);
        
        userDao.create(user2);
        Assert.assertNotNull(user2.getId());
        userDao.delete(user2);

    }
    
    @Test
    public void testUpdate(){
        User user1 = new User();
        
        user1.setGivenName("Jano");
        user1.setSurname("Slivka");
        user1.setEmail("jano.slivka@gmail.com");
        user1.setPhone("0915702236");
        user1.setPasswordHash("asda3");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Date joinedDate = new Date();
        user1.setJoinedDate(joinedDate);
        
        userDao.create(user1);
        user1.setGivenName("Janko");
        userDao.update(user1);
        Assert.assertEquals(userDao.findById(user1.getId()).getGivenName(),"Janko");
        userDao.delete(user1);

        
        User user2 = new User();
        
        user2.setGivenName("Milan");
        user2.setSurname("Hruska");
        user2.setEmail("milan.hruska@gmail.com");
        user2.setPhone("0918736436");
        user2.setPasswordHash("asderaa");
        user2.setPersonType(PersonType.NATURAL);
        user2.setRole(Role.CUSTOMER);        
        
        joinedDate = new Date();
        user2.setJoinedDate(joinedDate);
            
        userDao.create(user2);
        user2.setGivenName("Milanko");
        userDao.update(user2);
        Assert.assertEquals(userDao.findById(user2.getId()).getGivenName(),"Milanko");
        userDao.delete(user2);
    }
    
    @Test
    public void testDelete(){
        User user1 = new User();
        
        user1.setGivenName("t");
        user1.setSurname("t");
        user1.setEmail("t.t@gmail.com");
        user1.setPhone("0915702236");
        user1.setPasswordHash("asda3");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Date joinedDate = new Date();
        user1.setJoinedDate(joinedDate);
        
        
        User user2 = new User();
        
        user2.setGivenName("t1");
        user2.setSurname("t1");
        user2.setEmail("t1.t1@gmail.com");
        user2.setPhone("0918736436");
        user2.setPasswordHash("asderaa");
        user2.setPersonType(PersonType.NATURAL);
        user2.setRole(Role.CUSTOMER);        
        
        joinedDate = new Date();
        user2.setJoinedDate(joinedDate);
        
        userDao.create(user1);
        userDao.create(user2);
        
        Assert.assertEquals(userDao.findAll().size(), 2);
        userDao.delete(user1);
        Assert.assertEquals(userDao.findAll().size(), 1);
        userDao.delete(user2);
        Assert.assertEquals(userDao.findAll().size(), 0);
    }
    
    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void testNullEmail(){
        User user1 = new User();
        
        user1.setGivenName("Robo");
        user1.setSurname("Cvikla");

        user1.setEmail(null);
        user1.setPhone("0915702236");
        user1.setPasswordHash("asda3");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Date joinedDate = new Date();
        user1.setJoinedDate(joinedDate);
        
        userDao.create(user1);
    }
    
    @Test
    public void testFindById() {
        
        //USER1
        User user1 = new User();
        
        user1.setGivenName("Michal");
        user1.setSurname("Skaredy");
        user1.setEmail("michal.skaredy@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("asda123");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Calendar cal1 = Calendar.getInstance();
        user1.setJoinedDate(cal1.getTime());
        
        userDao.create(user1);
        User testUser = userDao.findById(user1.getId());
        
        Assert.assertEquals(user1.getSurname(), testUser.getSurname());
        Assert.assertEquals(user1.getGivenName(),testUser.getGivenName());
        Assert.assertEquals(user1.getEmail(), testUser.getEmail());
        Assert.assertEquals(user1.getPasswordHash(), testUser.getPasswordHash());
        Assert.assertEquals(user1.getPhone(), testUser.getPhone());
        Assert.assertEquals(user1.getRole(), testUser.getRole());
        Assert.assertEquals(user1.getPersonType(), testUser.getPersonType());
        
        //USER2
        User user2 = new User();
        
        user2.setGivenName("Jozef");
        user2.setSurname("Hnusny");
        user2.setEmail("jozef.hnusny@gmail.com");
        user2.setPhone("0918732436");
        user2.setPasswordHash("asder");
        user2.setPersonType(PersonType.NATURAL);
        user2.setRole(Role.CUSTOMER);        
        
        user2.setJoinedDate(cal1.getTime());
        
        userDao.create(user2);
        testUser = userDao.findById(user2.getId());
        
        Assert.assertEquals(user2.getSurname(), testUser.getSurname());
        Assert.assertEquals(user2.getGivenName(), testUser.getGivenName());
        Assert.assertEquals(user2.getEmail(), testUser.getEmail());
        Assert.assertEquals(user2.getPasswordHash(), testUser.getPasswordHash());
        Assert.assertEquals(user2.getPhone(), testUser.getPhone());
        Assert.assertEquals(user2.getRole(), testUser.getRole());
        Assert.assertEquals(user2.getPersonType(), testUser.getPersonType());

        assertNull(userDao.findById(Long.MAX_VALUE));      
        
        userDao.delete(user1);
        userDao.delete(user2);   
    }
    
    @Test
    public void testFindAll() {
        Set<User> users = new HashSet<>();
       
        User user1 = new User();
        
        user1.setGivenName("Test");
        user1.setSurname("Test");
        user1.setEmail("test.test@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("asda123");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Calendar cal1 = Calendar.getInstance();
        user1.setJoinedDate(cal1.getTime());
        
        User user2 = new User();
        
        user2.setGivenName("Test1");
        user2.setSurname("Test1");
        user2.setEmail("test1.test1@gmail.com");
        user2.setPhone("0918732436");
        user2.setPasswordHash("asder");
        user2.setPersonType(PersonType.NATURAL);
        user2.setRole(Role.CUSTOMER);        
        
        user2.setJoinedDate(cal1.getTime());
        
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
        User user1 = new User();
        
        user1.setGivenName("Test2");
        user1.setSurname("Test2");
        user1.setEmail("test2.test2@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("asda123");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Calendar cal1 = Calendar.getInstance();
        user1.setJoinedDate(cal1.getTime());
        
        userDao.create(user1);
        Assert.assertEquals(user1.getId(), userDao.findUserByEmail("test2.test2@gmail.com").getId());
    }
}
