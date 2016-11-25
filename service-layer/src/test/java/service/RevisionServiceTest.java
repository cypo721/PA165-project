/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RevisionDao;
import entity.*;
import enums.MachineType;
import enums.PersonType;
import enums.Role;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 *
 * @author Václav Zouzalík
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RevisionServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private RevisionDao revisionDao;

    @Autowired
    @InjectMocks
    private RevisionService revisionService;
    
    @BeforeMethod
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Revision testRevision;
    private Machine testMachine;
    private User testUser;
    
    @BeforeMethod
    public void prepareTestMachine()
    {
        testMachine = new Machine();
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
        testUser = new User();
        testUser.setGivenName("Julia");
        testUser.setSurname("Green");
        testUser.setEmail("julia@green.name");
        testUser.setPersonType(PersonType.NATURAL);
        testUser.setRole(Role.EMPLOYEE);
    }
    
    @BeforeMethod
    public void prepareTestRevision()
    {
        testRevision = new Revision();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 24);
        
        testRevision.setDateOfRevision(cal.getTime());
        testRevision.setUser(testUser);
        testRevision.setMachine(testMachine);
    }

    @Test
    public void testCreate() {
        Long id = 1L;
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            Revision revision = (Revision) arg;
            revision.setId(id);
            return null;
        }).when(revisionDao).create(any(Revision.class));

        revisionService.create(testRevision);
        org.testng.Assert.assertEquals(id, testRevision.getId());
    }
    
    public void testDelete()
    {
        Long id;
        revisionService.create(testRevision);
        id = testRevision.getId();
        revisionService.delete(testRevision);
        Assert.assertNull(revisionService.findById(id));
    }
    
    @Test
    public void findById() {
        when(revisionService.findById(2L)).thenReturn(testRevision);

        Assert.assertEquals(revisionService.findById(2L), testRevision);
        Assert.assertNull(revisionService.findById(-5L));
    }
    
    @Test
    public void findAllRentals() {
        when(revisionService.findAllRevisions()).thenReturn(Arrays.asList(testRevision));

        Assert.assertEquals(1, revisionService.findAllRevisions().size());
    }
}
