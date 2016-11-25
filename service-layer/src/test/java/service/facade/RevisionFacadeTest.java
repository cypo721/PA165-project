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
import org.testng.annotations.BeforeMethod;

/**
 * @author Václav Zouzalík
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

    private RevisionDTO testRevision;
    private MachineDTO testMachine;
    private UserDTO testUser;
    
    @BeforeMethod
    public void prepareTestMachine()
    {
        testMachine = new MachineDTO();
        testMachine.setName("Caterpilar");
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1996);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 24);
        
        testMachine.setDateOfBuy(cal.getTime());
        testMachine.setPricePerDay(new BigDecimal(5000));
        testMachine.setMachineType(MachineType.EXCAVATOR);
    }
    
    @BeforeMethod
    public void prepareTestUser()
    {
        testUser = new UserDTO();
        testUser.setGivenName("Julia");
        testUser.setSurname("Green");
        testUser.setEmail("julia@green.name");
        testUser.setPersonType(PersonType.NATURAL);
        testUser.setRole(Role.EMPLOYEE);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 24);
        
        testUser.setJoinedDate(cal.getTime());
    }
    
    @BeforeMethod
    public void prepareTestRevision()
    {
        testRevision = new RevisionDTO();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 24);
        
        testRevision.setDateOfRevision(cal.getTime());
        /*testRevision.setUser(testUser);
        testRevision.setMachine(testMachine);*/
    }
    
    @Test
    public void testCreateAndUpdate()
    {
        userFacade.createUser(testUser, "dewfrt");
        machineFacade.createMachine(testMachine);
        testRevision.setUser(testUser);
        testRevision.setMachine(testMachine);
        revisionFacade.createRevision(testRevision);
        Assert.assertNotNull(testRevision.getId());
        
        testRevision.setInfo("Test");
        revisionFacade.updateRevision(testRevision);
        Assert.assertEquals("Test", revisionFacade.findById(testRevision.getId()).getInfo());
    }
}