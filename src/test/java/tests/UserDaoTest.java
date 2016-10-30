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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author eduard
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    UserDao userDao;
    
    private User user1 = new User();
    private User user2;
    
    @BeforeClass
    public void beforeClass() {
        EntityManager em = emf.createEntityManager();
        user1 = new User();
        em.getTransaction().begin();
        
        user1.setGivenName("Michal");
        user1.setSurname("Skaredy");
        user1.setEmail("michal.skaredy@gmail.com");
        user1.setPhone("0915 702 446");
        user1.setPasswordHash("asda123");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);        
        
        Date joinedDate = new Date();
        user1.setJoinedDate(joinedDate);
        
        user2 = new User();
        
        user2.setGivenName("Jozef");
        user2.setSurname("Hnusny");
        user2.setEmail("jozef.hnusny@gmail.com");
        user2.setPhone("0918 732 436");
        user2.setPasswordHash("asder");
        user2.setPersonType(PersonType.NATURAL);
        user2.setRole(Role.CUSTOMER);        
        
        joinedDate = new Date();
        user2.setJoinedDate(joinedDate);
                
        em.persist(user1);
        em.persist(user2);
        //em.flush();
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void testCreate() {
        userDao.create(user1);
        userDao.create(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    public void testCreateNullName() {
        User user = new User();
        userDao.create(user);
    }

    @Test
    public void testFindById() {
        userDao.create(user1);
        userDao.create(user2);
        assertSame(user1, userDao.findById(user1.getId()));
        assertSame(user2, userDao.findById(user2.getId()));
        assertNull(userDao.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindAll() {
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);
        userDao.create(user1);
        userDao.create(user2);
        Set<User> result = new HashSet<>();
        result.addAll(userDao.findAll());
        assertEquals(result, users);
    }

}
