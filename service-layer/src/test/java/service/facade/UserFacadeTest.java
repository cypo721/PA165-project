package service.facade;

import dto.UserDTO;
import entity.User;
import enums.PersonType;
import enums.Role;
import facade.UserFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.BeanMappingService;
import service.UserService;
import service.config.ServiceConfiguration;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Marek Bohm on 25.11.2016.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    public static final String password = "password";
    @Mock
    private UserService userService;

    @Spy
    @Inject
    private BeanMappingService mappingService;

    @InjectMocks
    private UserFacade userFacade = new UserFacadeImpl();

    private User user1;
    private User user2;

    @BeforeMethod
    public void setUp() {
        setUpUsers();
        MockitoAnnotations.initMocks(this);
        when(userService.getUserById(user1.getId())).thenReturn(user1);
        when(userService.getUserById(user2.getId())).thenReturn(user2);
        when(userService.getUserByEmail(user2.getEmail())).thenReturn(user2);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
        when(userService.isEmployee(1L)).thenReturn(false);
        when(userService.isEmployee(1L)).thenReturn(true);
        when(userService.authenticate(2L, password)).thenReturn(true);
    }

    @Test
    public void createUser() {
        UserDTO userDTO = mappingService.mapTo(user1, UserDTO.class);
        userFacade.createUser(userDTO, password);
        Assert.assertNotNull(userFacade.findById(1L));

    }

    @Test
    public void authenticateSuccessTest() {
        UserDTO ua = mappingService.mapTo(user2, UserDTO.class);
        Assert.assertTrue(userFacade.authenticate(ua, password));
    }

    @Test
    public void authenticateFailureTest() {
        String modifiedPassword = "changed";
        UserDTO ua = mappingService.mapTo(user2, UserDTO.class);
        Assert.assertFalse(userFacade.authenticate(ua, modifiedPassword));
    }

    @Test
    public void findById() {
        UserDTO retDTO = userFacade.findById(2L);
        verify(userService).getUserById(2L);
        User retUser = mappingService.mapTo(retDTO, User.class);
        Assert.assertEquals(retUser, user2);
    }

    @Test
    public void findByLogin() {
        UserDTO retDTO = userFacade.findByEmail(user2.getEmail());
        verify(userService).getUserByEmail(user2.getEmail());
        User retUser = mappingService.mapTo(retDTO, User.class);
        Assert.assertEquals(retUser, user2);
    }

    @Test
    public void findAllUsersTest() {
        Collection<UserDTO> retDTO = userFacade.getAllUsers();
        verify(userService).getAllUsers();
        List<User> users = mappingService.mapTo(retDTO, User.class);
        Assert.assertEquals(users.size(), 2);
        Assert.assertTrue(users.contains(user1));
        Assert.assertTrue(users.contains(user2));
    }

    @Test
    public void isEmployeeTest() {

        UserDTO userDTO1 = mappingService.mapTo(user1, UserDTO.class);
        UserDTO userDTO2 = mappingService.mapTo(user2, UserDTO.class);
        Assert.assertTrue(userFacade.isEmployee(userDTO1));
        Assert.assertFalse(userFacade.isEmployee(userDTO2));
    }

    private void setUpUsers() {
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
    }
}