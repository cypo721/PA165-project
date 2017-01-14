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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Václav Zouzalík
 */
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
        prepareTestMachine();
        prepareTestUser();
        
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
        revisionService.create(testRevision);
        verify(revisionDao, times(1)).create(testRevision);
    }
    
    @Test
    public void testUpdate() {
        testRevision.setId(2L);
        revisionService.update(testRevision);
        verify(revisionDao, times(1)).update(testRevision);
    }
    
    @Test
    public void testDelete() {
        testRevision.setId(1L);
        revisionService.delete(testRevision);
        verify(revisionDao, times(1)).delete(testRevision);
    }
    
    @Test
    public void findById() {
        revisionService.findById(2L);
        verify(revisionDao, times(1)).findById(2L);
    }
    
    @Test
    public void findAllRentals() {
        Assert.assertEquals(revisionService.findAllRevisions().size(), 0);
        
        when(revisionDao.findAllRevisions()).thenReturn(Arrays.asList(testRevision));

        Assert.assertEquals(revisionService.findAllRevisions().size(), 1);
    }
}