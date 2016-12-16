///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import config.PersistenceSampleApplicationContext;
//import dao.MachineDao;
//import dao.RentalDao;
//import dao.UserDao;
//import entity.Machine;
//import entity.Rental;
//import entity.User;
//import enums.MachineType;
//import enums.PersonType;
//import enums.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
///**
// *
// * @author Václav Zouzalík
// */
//@Transactional
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
//public class RentalDaoTest extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private RentalDao rentalDao;
//
//    @Autowired
//    private MachineDao machineDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    private Machine machine1;
//    private User usr;
//
//    @BeforeClass
//    public void beforeClass(){
//        machine1 = new Machine();
//        machine1.setName("Cat");
//        machine1.setPricePerDay(BigDecimal.TEN);
//        machine1.setMachineType(MachineType.EXCAVATOR);
//        Calendar cal1 = Calendar.getInstance();
//        cal1.set(Calendar.YEAR, 2014);
//        cal1.set(Calendar.MONTH, Calendar.JANUARY);
//        cal1.set(Calendar.DAY_OF_MONTH, 6);
//        machine1.setDateOfBuy(cal1.getTime());
//        machine1.setDateOfLastRevision(cal1.getTime());
//        machineDao.create(machine1);
//
//        usr = new User();
//        usr.setGivenName("Albus");
//        usr.setSurname("Dumbledore");
//        usr.setPasswordHash("adfbgnh");
//        usr.setEmail("albus@hogwarts.edu");
//        usr.setPersonType(PersonType.NATURAL);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(Calendar.YEAR, 2016);
//        cal2.set(Calendar.MONTH, Calendar.JANUARY);
//        cal2.set(Calendar.DAY_OF_MONTH, 20);
//        usr.setJoinedDate(cal1.getTime());
//        usr.setPhone("800123456");
//        usr.setRole(Role.EMPLOYEE);
//        userDao.create(usr);
//    }
//
//    @Test
//    public void testCreate(){
//        Rental rent = getRental();
//        rent.setMachine(machine1);
//        rent.setUser(usr);
//
//        rentalDao.create(rent);
//
//        Assert.assertNotNull(rent.getId());
//        Assert.assertEquals(usr, rent.getUser());
//        Assert.assertEquals(machine1, rent.getMachine());
//    }
//
//    @Test
//    public void testfindById(){
//        Rental rent = getRental();
//        rent.setMachine(machine1);
//        rent.setUser(usr);
//
//        rentalDao.create(rent);
//        Assert.assertEquals(rent.getUser().getEmail(), rentalDao.findById(rent.getId()).getUser().getEmail());
//        Assert.assertEquals(rent.getMachine().getName(), rentalDao.findById(rent.getId()).getMachine().getName());
//        Assert.assertEquals(new Integer(5000), rentalDao.findById(rent.getId()).getPrice());
//    }
//
//    @Test
//    public void testUpdate(){
//        Rental rent = getRental();
//        rent.setMachine(machine1);
//        rent.setUser(usr);
//        rentalDao.create(rent);
//
//        rent.setPrice(8500);
//
//        rentalDao.update(rent);
//        Assert.assertEquals(new Integer(8500), rentalDao.findById(rent.getId()).getPrice());
//    }
//
//    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
//    public void testNullUser(){
//        Rental rent = getRental();
//        rent.setUser(null);
//        rent.setMachine(machine1);
//
//        rentalDao.create(rent);
//    }
//
//    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
//    public void testNullMachine(){
//        Rental rent = getRental();
//        rent.setUser(usr);
//        rent.setMachine(null);
//
//        rentalDao.create(rent);
//    }
//
//    @Test
//    public void testEquals() {
//        Rental rent1 = getRental();
//
//        Assert.assertEquals(rent1, rent1);
//
//        Rental rent2 = getRental();
//        rent2.setPrice(12000);
//
//        Assert.assertNotEquals(rent1, rent2);
//    }
//
//    private Rental getRental() {
//        Rental rent = new Rental();
//
//        Calendar cal1 = new GregorianCalendar();
//        cal1.set(2016, Calendar.OCTOBER, 30, 0, 0, 0);
//        rent.setDateFrom(cal1.getTime());
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(2016, Calendar.NOVEMBER, 1, 0, 0, 0);
//        rent.setDateTo(cal2.getTime());
//
//        rent.setPrice(5000);
//
//        return rent;
//    }
//
//}
