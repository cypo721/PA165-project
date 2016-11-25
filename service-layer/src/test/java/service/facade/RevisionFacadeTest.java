package service.facade;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import dto.*;
import enums.*;
import facade.*;
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
public class RevisionFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private RevisionFacade revisionFacade;

    @Autowired
    private MachineFacade machineFacade;

    @Autowired
    private UserFacade userFacade;

    

    private UserDTO getUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setGivenName("Alex");
        dto.setSurname("Mack");
        dto.setPasswordHash("fretgrz");
        dto.setEmail("alex@mack.name");
        dto.setPersonType(PersonType.NATURAL);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 25);
        dto.setJoinedDate(cal.getTime());
        dto.setRole(Role.EMPLOYEE);

        return dto;
    }

    private MachineDTO getMachineDTO(){
        MachineDTO dto = new MachineDTO();
        dto.setName("Caterpillar");
        dto.setPricePerDay(new BigDecimal(5000));
        dto.setMachineType(MachineType.EXCAVATOR);
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 17);
        dto.setDateOfBuy(cal.getTime());
        dto.setDateOfLastRevision(cal.getTime());

        return dto;
    }
    
    private RevisionDTO getRevisionDTO(){
        RevisionDTO dto = new RevisionDTO();
        Date now = new Date();
        
        dto.setDateOfRevision(now);
        dto.setFunctionable(true);
        dto.setInfo("Test");
        
        return dto;
    }

    @Test
    public void testRentalFacade(){
        MachineDTO m = getMachineDTO();
        m = machineFacade.findById(machineFacade.createMachine(m));
        
        UserDTO u = getUserDTO();
        u = userFacade.createUser(u,"123456");
        
        RevisionDTO rev = getRevisionDTO();
        rev.setMachine(m);
        rev.setUser(u);
        
        Assert.assertNotNull(revisionFacade.createRevision(rev));
        
        rev.setInfo("Test2");
        revisionFacade.updateRevision(rev);
        Assert.assertEquals("Test2", revisionFacade.findById(rev.getId()).getInfo());
        
        Assert.assertNotNull(revisionFacade.findAllRevisions());
    }
}