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
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author eduard
 */
@RunWith(MockitoJUnitRunner.class)
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
 
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
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
        
        machine5 = new Machine();
        machine5.setId(4L);
        machine5.setName("Ex 200");
        machine5.setPricePerDay(new BigDecimal("10.00"));
        machine5.setDateOfBuy(cal.getTime());
        machine5.setDateOfLastRevision(cal.getTime());
        machine5.setMachineType(MachineType.EXCAVATOR);
    }   
    
    @Test
    public void createMachineTest(){
        Long machineId = 1L;
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            Machine machine = (Machine) arg;
            machine.setId(machineId);
            return null;
        }).when(machineDao).create(any(Machine.class));

        machineService.create(machine1);
        Assert.assertEquals(machineId, machine1.getId());             
    } 
    
    @Test
    public void testDelete() {
        when(machineService.findById(eq(machine2.getId()))).thenReturn(machine2).thenReturn(null);
        Assert.assertNotNull(machineService.findById(machine2.getId()));
        machineService.delete(machine2);
        org.testng.Assert.assertNull(machineService.findById(machine2.getId()));
    }
    
    @Test(expectedExceptions = {DataAccessException.class})
    public void testDeleteNonExistingRental(){
        doThrow(new DataAccessException("Deleting non existing rental") {}).when(machineDao).delete(eq(machine3));
        machineService.delete(machine3);
    }
    
    @Test
    public void findAllMachine() {
        when(machineService.findAllMachines()).thenReturn(Arrays.asList(machine1, machine2));

        Assert.assertEquals(2, machineService.findAllMachines().size());
    }
    
    @Test
    public void findMachineById() {
        when(machineService.findById(2L)).thenReturn(machine2);

        Assert.assertEquals(machineService.findById(2L), machine2);
    }
    
    @Test
    public void findByNonExistingId() {
        when(machineService.findById(-3L)).thenReturn(null);

        Assert.assertNull(machineService.findById(-3L));
    }
    
    @Test
    public void testUpdate() {
        when(machineService.findById(2L)).thenReturn(machine2);
        doAnswer(invocation -> {
            Object arg = invocation.getArguments()[0];
            Machine machine = (Machine) arg;
            return machine;
        }).when(machineDao).update(any(Machine.class));
        machine2.setName("TestName");
        machineService.update(machine2);
        Machine updated = machineService.findById(2L);
        Assert.assertEquals(updated.getName(), machine2.getName());
    }
    
    @Test
    public void testGetMachinesInLastYearWithoutRevision() {        
        when(machineService.findAllMachines()).thenReturn(Arrays.asList(machine1, machine2, machine4, machine5));
             
        List<Machine> machines = machineService.getMachinesInLastYearWithoutRevision();
        
        Assert.assertEquals(3, machines.size());
    }
}
