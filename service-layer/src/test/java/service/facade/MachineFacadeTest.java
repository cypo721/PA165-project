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
import java.util.Calendar;
import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.config.ServiceConfiguration;

/**
 *
 * @author eduard
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=ServiceConfiguration.class)
public class MachineFacadeTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Autowired
    private MachineFacade machineFacade;
    
    @Test
    public void testCreateMachine() {
        MachineDTO m = getMachineDTO();
        Assert.assertNotNull(machineFacade.createMachine(m));
    }
    
    @Test
    public void testDeleteMachine() {
        MachineDTO m = getMachineDTO();
        Long id = machineFacade.createMachine(m);
        machineFacade.deleteMachine(id);
        Assert.assertEquals(machineFacade.findAllMachines().size(), 0);
    }
    
    @Test
    public void testUpdateMachine() {
        MachineDTO m = getMachineDTO();
        Long id = machineFacade.createMachine(m);
        m.setName("Crane 2000");
        
        machineFacade.updateMachine(m);
        
        Assert.assertEquals(machineFacade.findById(id).getName(), "Crane 2000");
    }
    
    @Test
    public void testFindById() {
        MachineDTO m = getMachineDTO();
        Long id = machineFacade.createMachine(m);
        
        Assert.assertNotNull(machineFacade.findById(id));
    }
    
    @Test
    public void testFindAllMachines() {
        MachineDTO m = getMachineDTO();
        MachineDTO m1 = getMachineDTO1();
        Long id = machineFacade.createMachine(m);
        Long id1 = machineFacade.createMachine(m1);
        
        Assert.assertEquals(machineFacade.findAllMachines().size(), 2);
    }
    
    private MachineDTO getMachineDTO() {
        MachineDTO dto = new MachineDTO();
        dto.setName("Cat");
        dto.setPricePerDay(BigDecimal.TEN);
        dto.setMachineType(MachineType.EXCAVATOR);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 6);
        dto.setDateOfBuy(cal.getTime());
        dto.setDateOfLastRevision(cal.getTime());  
        
        return dto;
    }
    
    private MachineDTO getMachineDTO1() {
        MachineDTO dto = new MachineDTO();
        dto.setName("Cat1");
        dto.setPricePerDay(BigDecimal.TEN);
        dto.setMachineType(MachineType.EXCAVATOR);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 6);
        dto.setDateOfBuy(cal.getTime());
        dto.setDateOfLastRevision(cal.getTime());  
        
        return dto;
    }
    
}
