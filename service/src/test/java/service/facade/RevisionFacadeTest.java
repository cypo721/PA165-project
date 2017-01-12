package service.facade;

import org.mockito.runners.MockitoJUnitRunner;
import dto.*;
import entity.*;
import enums.*;
import facade.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RevisionFacadeTest extends AbstractTransactionalTestNGSpringContextTests{
        
    @Mock
    private RevisionService revisionService;
    
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
    private Revision revision2;
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
        // revision.setId(1L);
        revision.setInfo("Test");
        revision.setIsFunctionable(true);
        revision.setDateOfRevision(cal.getTime());
        revision.setMachine(machine);
        revision.setUser(user);

        revision2 = new Revision();
        revision2.setId(2L);
        revision2.setInfo("Test2");
        revision2.setIsFunctionable(true);
        revision2.setDateOfRevision(cal.getTime());
        revision2.setMachine(machine);
        revision2.setUser(user);
    }
    
    @Test
    public void testCreateRevision() {
        when(revisionService.create(any(Revision.class))).thenReturn(revision);
        RevisionDTO revisionDTO = mappingService.mapTo(revision, RevisionDTO.class);
        revisionFacade.createRevision(revisionDTO);
        verify(revisionService).create(any(Revision.class));
    }
    
    @Test
    public void testUpdateRevision() {
        RevisionDTO revisionDTO = mappingService.mapTo(revision2, RevisionDTO.class);
        revisionDTO.setInfo("Test3");
        revisionFacade.updateRevision(revisionDTO);
        verify(revisionService).update(any(Revision.class));
    }

    @Test
    public void testFindById() {
        when(revisionService.findById(revision2.getId())).thenReturn(revision2);
        RevisionDTO revisionDTO = revisionFacade.findById(revision2.getId());
        verify(revisionService).findById(revisionDTO.getId());
    }

    @Test
    public void testFindAllRentals() {
        when(revisionService.findAllRevisions()).thenReturn(Arrays.asList(revision));
        List<RevisionDTO> dtos = revisionFacade.findAllRevisions();
        verify(revisionService).findAllRevisions();
        Assert.assertEquals(dtos.size(), 1);
    }
}