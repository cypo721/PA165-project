package service.facade;

import entity.Machine;
import entity.Rental;
import entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import dto.MachineDTO;
import dto.RentalDTO;
import dto.UserDTO;
import enums.MachineType;
import enums.PersonType;
import enums.Role;
import facade.MachineFacade;
import facade.RentalFacade;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.BeanMappingService;
import service.RentalService;
import service.config.ServiceConfiguration;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by pato on 23.11.2016.
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RentalFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalFacade rentalFacade = new RentalFacadeImpl();

    @Spy
    @Inject
    private BeanMappingService mappingService;

    private Rental rentalCurrent;
    private Rental rental1;
    private Rental rental2;
    private Machine machine2;

    @BeforeMethod
    public void setUp() {
        prepareRentals();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRental() {
        when(rentalService.create(any(Rental.class))).thenReturn(rental2);
        RentalDTO rental = mappingService.mapTo(rental2, RentalDTO.class);
        rentalFacade.createRental(rental);
        verify(rentalService).create(any(Rental.class));
    }

    @Test
    public void testFindAllRentals() {
        when(rentalService.findAllRentals()).thenReturn(Arrays.asList(rental1, rental2));
        List<RentalDTO> dtos = rentalFacade.findAllRentals();
        verify(rentalService).findAllRentals();
        Assert.assertEquals(dtos.size(), 2);
    }

    @Test
    public void testFindById() {
        when(rentalService.findRentalById(rental2.getId())).thenReturn(rental2);
        RentalDTO retDto = rentalFacade.findById(rental2.getId());
        verify(rentalService).findRentalById(2L);
    }

    @Test
    public void testDeleteRental() {
        RentalDTO rental = mappingService.mapTo(rental2, RentalDTO.class);
        rentalFacade.deleteRental(rental.getId());
        verify(rentalService).delete(any(Rental.class));
    }

    @Test
    public void testUpdateRental() {
        RentalDTO rental = mappingService.mapTo(rental2, RentalDTO.class);
        rental.setPrice(1);
        rentalFacade.updateRental(rental);
        verify(rentalService).update(any(Rental.class));
    }

    @Test
    public void testFindAllMachinesRentedCurrentWeek(){

        when(rentalService.findAllMachinesRentedCurrentWeek()).thenReturn(Arrays.asList(machine2));

        List<MachineDTO> dtos = rentalFacade.findAllMachinesRentedCurrentWeek();
        verify(rentalService).findAllMachinesRentedCurrentWeek();
        Assert.assertEquals(dtos.size(), 1);

    }


    private void prepareRentals(){
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2016);
        cal2.set(Calendar.MONTH, Calendar.JANUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 20);

        User user1 = new User();
        user1.setId(8L);
        user1.setGivenName("test");
        user1.setSurname("test");
        user1.setEmail("test.test@gmail.com");
        user1.setPhone("0915702446");
        user1.setPasswordHash("test");
        user1.setPersonType(PersonType.LEGAL);
        user1.setRole(Role.EMPLOYEE);

        User user2 = new User();
        user2.setGivenName("Albus");
        user2.setSurname("Dumbledore");
        user2.setPasswordHash("adfbgnh");
        user2.setEmail("albus@hogwarts.edu");
        user2.setPersonType(PersonType.NATURAL);
        user2.setJoinedDate(cal2.getTime());
        user2.setPhone("800123456");
        user2.setRole(Role.EMPLOYEE);

        Machine machine1 = new Machine();
        machine1.setName("Test1");
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.CRANE);
        machine1.setDateOfBuy(cal2.getTime());
        machine1.setDateOfLastRevision(cal2.getTime());

        machine2 = new Machine();
        machine2.setName("Test2");
        machine2.setPricePerDay(BigDecimal.TEN);
        machine2.setMachineType(MachineType.LORRY);
        machine2.setDateOfBuy(cal2.getTime());
        machine2.setDateOfLastRevision(cal2.getTime());

        rental1 = new Rental();
        Calendar cal1 = new GregorianCalendar();
        cal1.set(2016, Calendar.OCTOBER, 30, 0, 0, 0);
        rental1.setDateFrom(cal1.getTime());
        Calendar cal3 = Calendar.getInstance();
        cal2.set(2016, Calendar.NOVEMBER, 1, 0, 0, 0);
        rental1.setDateTo(cal3.getTime());
        rental1.setPrice(5000);
        rental1.setUser(user1);
        rental1.setMachine(machine1);

        rental2 = new Rental();
        rental2.setId(2L);
        rental2.setDateFrom(cal2.getTime());
        rental2.setDateTo(cal1.getTime());
        rental2.setPrice(5000);
        rental2.setUser(user2);
        rental2.setMachine(machine1);

        /**
         * current rental
         */
        rentalCurrent = new Rental();
        rentalCurrent.setId(3L);

        Calendar calCurrentFrom = new GregorianCalendar();
        calCurrentFrom.set(2016, Calendar.MONTH, Calendar.DATE -2, 0, 0, 0);
        rentalCurrent.setDateFrom(calCurrentFrom.getTime());

        Calendar calCurrentTo = new GregorianCalendar();
        calCurrentTo.set(2016, Calendar.MONTH, Calendar.DATE + 10, 0, 0, 0);
        rentalCurrent.setDateTo(calCurrentTo.getTime());

        rentalCurrent.setPrice(5000);
        rentalCurrent.setUser(user1);
        rentalCurrent.setMachine(machine2);
    }

}
