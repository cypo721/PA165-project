package service;

import dao.UserDao;
import entity.Machine;
import entity.Rental;
import entity.User;
import enums.PersonType;
import enums.Role;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import java.util.Arrays;
import java.util.Calendar;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Marek Bohm on 25.11.2016.
 */

@ContextConfiguration(classes=ServiceConfiguration.class)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;
    private User user3;

    @BeforeTest
    public void setUp() {

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2016);
        cal2.set(Calendar.MONTH, Calendar.JANUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 20);

        user1 = new User();
        user1.setId(1L);
        user1.setGivenName("Lucius");
        user1.setSurname("Malfoy");
        user1.setEmail("luc.malfoy@gmail.com");
        user1.setPhone("0904355662");
//        user1.setPasswordHash("test");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);
        user1.setJoinedDate(cal2.getTime());


        user2 = new User();
        user2.setId(2L);
        user2.setGivenName("Albus");
        user2.setSurname("Dumbledore");
        user2.setPasswordHash("adfbgnh");
        user2.setEmail("albus@hogwarts.edu");
        user2.setPersonType(PersonType.NATURAL);
        user2.setJoinedDate(cal2.getTime());
        user2.setPhone("800123456");
        user2.setRole(Role.EMPLOYEE);

        user3 = new User();
        user3.setId(3L);
        user3.setGivenName("Donald");
        user3.setSurname("Trump");
        user3.setPasswordHash("qwerty");
        user3.setEmail("donald@trump.com");
        user3.setPersonType(PersonType.NATURAL);
        user3.setJoinedDate(cal2.getTime());
        user3.setPhone("18004696323");
        user3.setRole(Role.EMPLOYEE);


    }

    @BeforeMethod
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2, user3));

        Assert.assertEquals(userService.getAllUsers().size(), 3);
    }

    @Test
    public void getUserById() {
        when(userService.getUserById(2L)).thenReturn(user2);

        Assert.assertEquals(userService.getUserById(2L), user2);
    }

    @Test
    public void findByNonExistingId() {
        when(userService.getUserById(-3L)).thenReturn(null);

        Assert.assertNull(userService.getUserById(-3L));
    }

    @Test
    public void createUser() {
        Long userId = 1L;
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            User user = (User) arg;
            user.setId(userId);
            return null;
        }).when(userDao).create(any(User.class));

        userService.createUser(user1, "password");
        Assert.assertEquals(userId, user1.getId());
    }

    @Test
    public void authenticate() {

        when(userService.getUserById(1L)).thenReturn(user1);

        Long userId = 1L;
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            User user = (User) arg;
            user.setId(userId);
            return null;
        }).when(userDao).create(any(User.class));


        userService.createUser(user1, "password");

        Assert.assertTrue(userService.authenticate(1L,"password"));

    }

    @Test
    public void changePassword() {

        when(userService.getUserById(1L)).thenReturn(user1);

        Long userId = 1L;
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            User user = (User) arg;
            user.setId(userId);
            return null;
        }).when(userDao).create(any(User.class));

        userService.createUser(user1, "password");
        userService.changePassword(1L, "changed");
        Assert.assertTrue(userService.authenticate(1L,"changed"));


    }

    @Test
    public void isEmployee() {

        when(userService.getUserById(1L)).thenReturn(user1);

        Long userId = 1L;
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            User user = (User) arg;
            user.setId(userId);
            return null;
        }).when(userDao).create(any(User.class));

        userService.createUser(user1, "password");
        Assert.assertTrue(userService.isEmployee(1L));
    }
}
