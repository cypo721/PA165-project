/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import config.PersistenceSampleApplicationContext;
import dao.RentalDao;
import dao.RentalDaoImpl;
import entity.Rental;
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
    
    @Test
    public void testCreate(){
        Rental rental = new Rental();
        rental.setEmail("somebody@example.org");

        Date dat1 = new Date(116, 9, 30);
        Date dat2 = new Date(116, 10, 1);

        rental.setDateFrom(dat1);
        rental.setDateTo(dat2);

        rental.setPrice(2000);
        rental.setMachineId(new Long(666));

        rentalDao.create(rental);
        Assert.assertNotNull(rental.getId());
        rentalDao.delete(rental);
    }

    /*@Test
    public void testUpdate(){
        Rental rental = new Rental();
        rental.setEmail("somebody@example.org");

        Date dat1 = new Date(116, 9, 30);
        Date dat2 = new Date(116, 10, 1);

        rental.setDateFrom(dat1);
        rental.setDateTo(dat2);

        rental.setPrice(2000);
        rental.setMachineId(new Long(666));

        rentalDao.create(rental);

        Date dat3 = new Date(116, 10, 2);
        Date dat4 = new Date(116, 10, 5);

        //rental.setEmail("someone@example.org");
        rental.setDateFrom(dat3);
        rental.setDateTo(dat4);
        rentalDao.update(rental);

        //Assert.assertEquals(rentalDao.findById(rental.getId()).getEmail(),"someone@example.org");
        Assert.assertEquals(rentalDao.findById(rental.getId()).getDateFrom(), dat3);
        Assert.assertEquals(rentalDao.findById(rental.getId()).getDateTo(), dat4);

        rentalDao.delete(rental);
    }*/

}
