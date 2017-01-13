/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MachineDao;
import entity.Machine;
import enums.MachineType;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.dao.DataAccessException;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeTest;

/**
 *
 * @author eduard
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class MachineServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private MachineDao machineDao;
       
    @Autowired
    @InjectMocks
    private MachineService machineService;
    
    
    private Machine machine1;
    private Machine machine2;
    private Machine machine3;
    private Machine machine4;
    private Machine machine5;
 
    
    @BeforeTest
    public void setUpMachines()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 20);

        machine1 = new Machine();
        machine1.setName("Crane 2500+");
        machine1.setPricePerDay(new BigDecimal("15.36"));
        machine1.setDateOfBuy(cal.getTime());
        machine1.setDateOfLastRevision(cal.getTime());
        machine1.setMachineType(MachineType.CRANE);

        machine2 = new Machine();
        machine2.setId(2L);
        machine2.setName("Excavator 2500+");
        machine2.setPricePerDay(new BigDecimal("20.00"));
        machine2.setDateOfBuy(cal.getTime());
        machine2.setDateOfLastRevision(cal.getTime());
        machine2.setMachineType(MachineType.EXCAVATOR);
        
        machine4 = new Machine();
        machine4.setId(3L);
        machine4.setName("Excavator 200");
        machine4.setPricePerDay(new BigDecimal("10.00"));
        machine4.setDateOfBuy(cal.getTime());
        machine4.setDateOfLastRevision(cal.getTime());
        machine4.setMachineType(MachineType.EXCAVATOR);
        
        cal.set(Calendar.YEAR, 2016);
       
    }   
    
    @BeforeMethod
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreate(){
       

        machineService.create(machine1);
        verify(machineDao, times(1)).create(machine1);
    } 
    
    @Test
    public void testDelete() {
        machineService.delete(machine3);
        verify(machineDao, times(1)).delete(machine3);

    }
    
    @Test(expectedExceptions = {DataAccessException.class})
    public void testDeleteNonExistingRental(){
        doThrow(new DataAccessException("Deleting non existing machine") {}).when(machineDao).delete(any(Machine.class));
        machineService.delete(machine5);
        
        fail("Expected dataAccessException");

    }
    
    @Test
    public void findAllMachine() {
        machineService.findAllMachines();
        verify(machineDao, times(1)).findAllMachines();
                                
    }
    
    @Test
    public void findMachineById() {
        machineService.findById(2L);
        verify(machineDao, times(1)).findById(2L);
    }
    
    @Test(expectedExceptions = {PersistenceException.class})
    public void findByNonExistingId() {
        doThrow(new PersistenceException("")).when(machineDao).findById(any(Long.class));
        machineService.findById(50L);
        
        fail("Expected PersistanceException");

    }
    
    @Test
    public void testUpdate() {
        machineService.update(machine2);
        verify(machineDao, times(1)).update(machine2);
    }
    
    @Test
    public void testGetMachinesInLastYearWithoutRevision() {        
        when(machineService.findAllMachines()).thenReturn(Arrays.asList(machine1, machine2, machine4));
             
        List<Machine> machines = machineService.getMachinesInLastYearWithoutRevision();
        
        Assert.assertEquals(3, machines.size());
    }
}
