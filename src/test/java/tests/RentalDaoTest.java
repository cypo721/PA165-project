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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        
        Calendar cal1 = new GregorianCalendar();
        cal1.set(2016, Calendar.OCTOBER, 30, 0, 0);
        
        Calendar cal2 = new GregorianCalendar();
        cal2.set(2016, Calendar.NOVEMBER, 1, 0, 0);

        rental.setDateFrom(cal1.getTime());
        rental.setDateTo(cal2.getTime());

        rental.setPrice(2000);
        rental.setUser(userDao.findUserByEmail("test.test@gmail.com"));

        Machine machine = new Machine();
        machine.setName("Cat");
        machine.setDateOfBuy(cal1.getTime());
        machine.setDateOfLastRevision(cal1.getTime());
        machine.setPricePerDay(new BigDecimal(10));
        machine.setMachineType(MachineType.EXCAVATOR);

        rental.setMachine(machine);

        machineDao.create(machine);

        rentalDao.create(rental);
        Assert.assertNotNull(rental.getId());
        rentalDao.delete(rental);
    }

}
