package service.facade;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import dto.*;
import entity.*;
import enums.*;
import facade.*;
import org.junit.Assert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.inject.Inject;
import static org.mockito.Matchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import service.BeanMappingService;
import service.*;
import static org.mockito.Mockito.when;

/**
 * @author Václav Zouzalík
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RevisionFacadeTest extends AbstractTransactionalTestNGSpringContextTests{
        
    @Mock
    private RevisionService revisionService;
    
    @Mock
    private MachineService machineService;
    
    @Mock
    private UserService userService;
    
    @Spy
    @Inject
    private BeanMappingService mappingService;
    
    @InjectMocks
    private final RevisionFacade revisionFacade = new RevisionFacadeImpl();
    
    @InjectMocks
    private final MachineFacade machineFacade = new MachineFacadeImpl();
    
    @InjectMocks
    private final UserFacade userFacade = new UserFacadeImpl();
    
    private Revision revision;
    private Machine machine;
    private User user;
    
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 6);
        
        machine = new Machine();
        machine.setId(1L);
        machine.setName("Caterpilar");
        machine.setPricePerDay(new BigDecimal(5000));
        machine.setMachineType(MachineType.EXCAVATOR);
        machine.setDateOfBuy(cal.getTime());
        machine.setDateOfLastRevision(cal.getTime());
        
        user = new User();
        user.setId(1L);
        user.setGivenName("Julia");
        user.setSurname("Green");
        user.setEmail("julia@green.name");
        user.setPasswordHash("qefwre");
        user.setPhone("56109");
        user.setPersonType(PersonType.NATURAL);
        user.setRole(Role.EMPLOYEE);
        user.setJoinedDate(cal.getTime());
        
        revision = new Revision();
        revision.setId(1L);
        revision.setInfo("Test");
        revision.setIsFunctionable(true);
        revision.setDateOfRevision(cal.getTime());
        revision.setMachine(machine);
        revision.setUser(user);
    }
    
    /*@Test
    public void testCreateRevision() {        
        when(machineService.create(any(Machine.class))).thenReturn(machine);
        MachineDTO mDTO = mappingService.mapTo(machine, MachineDTO.class);
        machineFacade.createMachine(mDTO);
        
        when(userService.createUser(any(User.class), any(String.class))).thenReturn(user);
        UserDTO uDTO = mappingService.mapTo(user, UserDTO.class);
        userFacade.createUser(uDTO, "dwefwtr");
        
        when(revisionService.create(any(Revision.class))).thenReturn(revision);
        RevisionDTO rDTO = mappingService.mapTo(revision, RevisionDTO.class);
        revisionFacade.createRevision(rDTO);
        Assert.assertNotNull(rDTO.getId());
    }*/
}