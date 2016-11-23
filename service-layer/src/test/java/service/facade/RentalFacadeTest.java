package service.facade;

import dto.MachineDTO;
import dto.RentalDTO;
import dto.UserDTO;
import facade.RentalFacade;
import org.junit.Assert;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by pato on 23.11.2016.
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RentalFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private RentalFacade rentalFacade;

    @Test
    public void testCreateRental(){

        Assert.assertNotNull(rentalFacade.createRental(getRentalDTO()));
    }


    private RentalDTO getRentalDTO(){
        RentalDTO dto = new RentalDTO();
        Date now = new Date();

        dto.setPrice(10);
        dto.setDateFrom(now);
        dto.setDateTo(now);
        dto.setMachine(new MachineDTO());
        //dto.setUser(new UserDTO());

        return dto;
    }
}
