/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import config.PersistenceSampleApplicationContext;
import dao.MachineDao;
import dao.RentalDao;
import dao.RentalDaoImpl;
import dao.UserDao;
import entity.Machine;
import entity.Rental;
import enums.MachineType;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Václav Zouzalík
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class RentalDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private RentalDao rentalDao;
    
    @Autowired
    private MachineDao machineDao;
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testCreate(){
        Rental rental = new Rental();

        Date dat1 = new Date(116, 9, 30);
        Date dat2 = new Date(116, 10, 1);

        rental.setDateFrom(dat1);
        rental.setDateTo(dat2);

        rental.setPrice(2000);
        
        rental.setUser(userDao.findUserByEmail("test.test@gmail.com"));
        
        Machine machine = new Machine();
        machine.setName("Cat");
        machine.setDateOfBuy(dat1);
        machine.setDateOfLastRevision(dat1);
        machine.setPricePerDay(new BigDecimal(10));
        machine.setMachineType(MachineType.EXCAVATOR);
        
        rental.setMachine(machine);
        
        machineDao.create(machine);

        rentalDao.create(rental);
        Assert.assertNotNull(rental.getId());
        rentalDao.delete(rental);
    }

}
