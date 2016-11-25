/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.facade;

import dto.MachineDTO;
import entity.Machine;
import enums.MachineType;
import facade.MachineFacade;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.BeanMappingService;
import service.MachineService;
import service.config.ServiceConfiguration;

/**
 *
 * @author eduard
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class MachineFacadeTest extends AbstractTransactionalTestNGSpringContextTests{
        
    @Mock
    private MachineService machineService;
    
    @Spy
    @Inject
    private BeanMappingService mappingService;

    
    @InjectMocks
    private MachineFacade machineFacade = new MachineFacadeImpl();
    
    private Machine machine1;
    private Machine machine2;
    
    @BeforeMethod
    public void setUp() {
        setUpMachines();
        MockitoAnnotations.initMocks(this);
        when(machineService.findById(machine2.getId())).thenReturn(machine2);
        when(machineService.findAllMachines()).thenReturn(Arrays.asList(machine1, machine2));
    }
    
    @Test
    public void testCreateMachine() {        
        when(machineService.create(any(Machine.class))).thenReturn(machine1);
        MachineDTO mDTO = mappingService.mapTo(machine1, MachineDTO.class);
        machineFacade.createMachine(mDTO);
        verify(machineService).create(any(Machine.class));
    }
    
    @Test
    public void testDeleteMachine() {
        MachineDTO mDTO = mappingService.mapTo(machine1, MachineDTO.class);
        machineFacade.deleteMachine(mDTO.getId());
        verify(machineService).delete(any(Machine.class));
    }
    
    @Test
    public void testUpdateMachine() {
        MachineDTO mDTO = mappingService.mapTo(machine1, MachineDTO.class);
        mDTO.setName("Crane 2000");
        machineFacade.updateMachine(mDTO);
        verify(machineService).update(any(Machine.class));
    }
    
    @Test
    public void testFindById() {
             
        when(machineService.findById(machine2.getId())).thenReturn(machine2);
        MachineDTO mDto = machineFacade.findById(machine2.getId());
        verify(machineService).findById(2L);

    }
    
    @Test
    public void testFindAllMachines() {
        List<MachineDTO> retDTO = machineFacade.findAllMachines();
        verify(machineService).findAllMachines();
        List<Machine> machines = mappingService.mapTo(retDTO, Machine.class);
        assertEquals(machines.size(), 2);
        assertTrue(machines.contains(machine1));
        assertTrue(machines.contains(machine2));
    }
    
    private void setUpMachines() {
        machine1 = new Machine();
        machine1.setId(1L);
        machine1.setName("Cat");
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.EXCAVATOR);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 6);
        machine1.setDateOfBuy(cal.getTime());
        machine1.setDateOfLastRevision(cal.getTime());
        
        machine2 = new Machine();
        machine2.setId(2L);
        machine2.setName("Cat");
        machine2.setPricePerDay(BigDecimal.TEN);
        machine2.setMachineType(MachineType.EXCAVATOR);
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        machine2.setDateOfBuy(cal.getTime());
        machine2.setDateOfLastRevision(cal.getTime());
        
    }
}
