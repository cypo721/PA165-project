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
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.MachineService;
import service.MachineServiceImpl;
import service.config.ServiceConfiguration;

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
    
    
    private Machine machine;
    private Machine machine2;
 
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setUpMachines()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 20);

        machine = new Machine();
        machine.setId(1L);
        machine.setName("Crane 2500+");
        machine.setPricePerDay(new BigDecimal("15.36"));
        machine.setDateOfBuy(cal.getTime());
        machine.setDateOfLastRevision(cal.getTime());
        machine.setMachineType(MachineType.CRANE);
        
        machine2 = new Machine();
        machine2.setId(2L);
        machine2.setName("Excavator 2500+");
        machine2.setPricePerDay(new BigDecimal("20.00"));
        machine2.setDateOfBuy(cal.getTime());
        machine2.setDateOfLastRevision(cal.getTime());
        machine2.setMachineType(MachineType.EXCAVATOR);
    }   
    

    
    @Test
    public void createMachineTest(){
        machineService.create(machine);
        verify(machineDao).create(machine);               
    } 
    
    @Test
    public void findAllMachine() {
        when(machineService.findAllMachines()).thenReturn(Arrays.asList(machine, machine2));

        Assert.assertEquals(machineService.findAllMachines().size(), 2);
    }
    
    @Test
    public void findMachineById() {
        when(machineService.findById(2L)).thenReturn(machine2);

        Assert.assertEquals(machineService.findById(2L), machine2);
    }
}
