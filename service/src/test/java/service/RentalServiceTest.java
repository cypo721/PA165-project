package service;

import dao.RentalDao;
import entity.Machine;
import entity.Rental;
import entity.User;
import enums.MachineType;
import enums.PersonType;
import enums.Role;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;

/**
 * Created by pato on 23.11.2016.
 */
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RentalServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private RentalDao rentalDao;

    @Autowired
    @InjectMocks
    private RentalService rentalService;
    @Autowired
    @InjectMocks
    private MachineService machineService;

    private User user1;
    private User user2;
    private Machine machine1;
    private Machine machine2;
    private Rental rental1;
    private Rental rental2;
    private Rental rental3;

    @BeforeTest
    public void setUp() {
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2016);
        cal2.set(Calendar.MONTH, Calendar.JANUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 20);

        user1 = new User();
        user1.setId(8L);
        user1.setGivenName("test");
        user1.setSurname("test");
        user1.setEmail("test.test@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("test");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);

        user2 = new User();
        user2.setGivenName("Albus");
        user2.setSurname("Dumbledore");
        user2.setPasswordHash("adfbgnh");
        user2.setEmail("albus@hogwarts.edu");
        user2.setPersonType(PersonType.NATURAL);
        user2.setJoinedDate(cal2.getTime());
        user2.setPhone("800123456");
        user2.setRole(Role.EMPLOYEE);

        machine1 = new Machine();
        machine1.setName("Test1");
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.CRANE);
        machine1.setDateOfBuy(cal2.getTime());
        machine1.setDateOfLastRevision(cal2.getTime());

        machine2 = new Machine();
        machine2.setName("Test2");
        machine2.setPricePerDay(BigDecimal.TEN);
        machine2.setMachineType(MachineType.EXCAVATOR);
        machine2.setDateOfBuy(cal2.getTime());
        machine2.setDateOfLastRevision(cal2.getTime());

        rental1 = new Rental();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_MONTH,-15);
        rental1.setDateFrom(cal1.getTime());
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.DAY_OF_MONTH,-15);
        rental1.setDateTo(cal3.getTime());
        rental1.setPrice(5000);
        rental1.setUser(user1);
        rental1.setMachine(machine2);

        rental2 = new Rental();
        rental2.setId(2L);
        rental2.setDateFrom(new Date());
        rental2.setDateTo(new Date());
        rental2.setPrice(5000);
        rental2.setUser(user2);
        rental2.setMachine(machine1);

    }

    @BeforeMethod
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllRentals() {
//        when(rentalService.findAllRentals()).thenReturn(Arrays.asList(rental1, rental2));
        rentalService.findAllRentals();
        verify(rentalDao, times(1)).findAllRentals();
//        Assert.assertEquals(rentalService.findAllRentals().size(), 2);
    }

    @Test
    public void findById() {
      //  when(rentalService.findRentalById(2L)).thenReturn(rental2);
        rentalService.findRentalById(2L);
        verify(rentalDao, times(1)).findById(2L);
//        Assert.assertEquals(rentalService.findRentalById(2L), rental2);
    }

    @Test(expectedExceptions = {PersistenceException.class})
    public void findByNonExistingId() {
//        long nonExitstingId = -3L;
//        when(rentalService.findRentalById(-3L)).thenReturn(null);
        doThrow(new PersistenceException("")).when(rentalDao).findById(any(Long.class));
        rentalService.findRentalById(2L);

        fail("Expected PersistanceException");

//        Assert.assertNull(rentalService.findRentalById(-3L));
    }

    @Test
    public void testCreate() {
//        Long userId = 1L;
//        doAnswer(invocation -> {
//            Object arg = invocation.getArguments()[0];
//            Rental rental = (Rental) arg;
//            rental.setId(userId);
//            return null;
//        }).when(rentalDao).create(any(Rental.class));

        rentalService.create(rental1);
        verify(rentalDao, times(1)).create(rental1);
//        Assert.assertEquals(userId, rental1.getId());
    }

    @Test
    public void testDelete() {
//        when(rentalService.findRentalById(eq(rental2.getId()))).thenReturn(rental2).thenReturn(null);
//        Assert.assertNotNull(rentalService.findRentalById(rental2.getId()));
        rentalService.delete(rental2);
        verify(rentalDao, times(1)).delete(rental2);
//        Assert.assertNull(rentalService.findRentalById(rental2.getId()));
    }

    @Test(expectedExceptions = {DataAccessException.class})
    public void testDeleteNonExistingRental(){
//        doThrow(new DataAccessException("Deleting non existing rental") {}).when(rentalDao).delete(eq(rental3));
        doThrow(new DataAccessException("deleting") {}).when(rentalDao).delete(any(Rental.class));

        rentalService.delete(rental3);

        fail("Expected dataAccessException");
    }

    @Test
    public void testUpdate() {
//        when(rentalService.findRentalById(2L)).thenReturn(rental2);
//        doAnswer(invocation -> {
//            Object arg = invocation.getArguments()[0];
//            Rental rental = (Rental) arg;
//            return rental;
//        }).when(rentalDao).update(any(Rental.class));
//        rental2.setPrice(999);
        rentalService.update(rental2);
        verify(rentalDao, times(1)).update(rental2);
//        Rental updated = rentalService.findRentalById(2L);
//        Assert.assertEquals(updated.getPrice(), rental2.getPrice());
    }

    @Test
    public void testfindAllMachinesRentedCurrentWeek() {
        when(rentalService.findAllRentals()).thenReturn(Arrays.asList(rental1, rental2));

        List<Machine> machines = rentalService.findAllMachinesRentedCurrentWeek();

        Assert.assertEquals(machines.size(), 1);
    }
}
