package service.facade;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import dto.MachineDTO;
import dto.RentalDTO;
import dto.UserDTO;
import enums.MachineType;
import enums.PersonType;
import enums.Role;
import facade.MachineFacade;
import facade.RentalFacade;
import facade.UserFacade;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pato on 23.11.2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RentalFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private RentalFacade rentalFacade;

    @Autowired
    private MachineFacade machineFacade;

    @Autowired
    private UserFacade userFacade;


    @Test
    public void testCreateRental(){
        MachineDTO m = getMachineDTO();
        m = machineFacade.findById(machineFacade.createMachine(m));
        UserDTO u = getUserDTO();
        u = userFacade.createUser(u,"heslo");
        RentalDTO r = getRentalDTO();
        r.setMachine(m);
        r.setUser(u);
        Assert.assertNotNull(rentalFacade.createRental(r));
    }


    private RentalDTO getRentalDTO(){
        RentalDTO dto = new RentalDTO();
        Date now = new Date();

        dto.setPrice(10);
        dto.setDateFrom(now);
        dto.setDateTo(now);
        dto.setMachine(new MachineDTO());
        return dto;
    }

    private UserDTO getUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setGivenName("Albus");
        dto.setSurname("Dumbledore");
        dto.setPasswordHash("adfbgnh");
        dto.setEmail("albus@hogwarts.edu");
        dto.setPersonType(PersonType.NATURAL);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2016);
        cal2.set(Calendar.MONTH, Calendar.JANUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        dto.setJoinedDate(cal2.getTime());
        dto.setPhone("800123456");
        dto.setRole(Role.EMPLOYEE);

        return dto;
    }

    private MachineDTO getMachineDTO(){
        MachineDTO dto = new MachineDTO();
        dto.setName("Cat");
        dto.setPricePerDay(BigDecimal.TEN);
        dto.setMachineType(MachineType.EXCAVATOR);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2014);
        cal1.set(Calendar.MONTH, Calendar.JANUARY);
        cal1.set(Calendar.DAY_OF_MONTH, 6);
        dto.setDateOfBuy(cal1.getTime());
        dto.setDateOfLastRevision(cal1.getTime());

        return dto;
    }
}
