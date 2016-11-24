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
import java.util.Calendar;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

/**
 *
 * @author Václav Zouzalík
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class RevisionServiceTest {
    
    @Mock
    private RevisionDao revisionDao;

    @Autowired
    @InjectMocks
    private RevisionService revisionService;
    
    @BeforeClass
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
    public void testCreateAndUpdate()
    {
        revisionService.create(testRevision);
        Assert.assertNotNull(testRevision.getId());
        testRevision.setInfo("Test");
        revisionService.update(testRevision);
        Assert.assertEquals("Test", revisionService.findById(testRevision.getId()).getInfo());
    }
    
    public void testDelete()
    {
        Long id;
        revisionService.create(testRevision);
        id = testRevision.getId();
        revisionService.delete(testRevision);
        Assert.assertNull(revisionService.findById(id));
    }
}
