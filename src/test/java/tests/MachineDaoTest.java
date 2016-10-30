package tests;

import config.PersistenceSampleApplicationContext;
import dao.MachineDao;
import entity.Machine;
import enums.MachineType;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ExpectedExceptionsAnnotation;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by pato on 30.10.2016.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class MachineDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    MachineDao machineDao;

//    private Machine machine1;
//    private Machine machine2;
//
//    @BeforeClass
//    public void beforeClass(){
//        machine1 = new Machine();
//        machine1.setName("Test1");
//        machine1.setPricePerDay(BigDecimal.TEN);
//        machine1.setMachineType(MachineType.CRANE);
//        Calendar cal1 = Calendar.getInstance();
//        cal1.set(Calendar.YEAR, 2011);
//        cal1.set(Calendar.MONTH, 0);
//        cal1.set(Calendar.DAY_OF_MONTH, 20);
//        machine1.setDateOfBuy(cal1.getTime());
//        machine1.setDateOfLastRevision(cal1.getTime());
//
//        machine2 = new Machine();
//        machine2.setName("Test2");
//        machine2.setPricePerDay(BigDecimal.ONE);
//        machine2.setMachineType(MachineType.LORRY);
//        Calendar cal2 = Calendar.getInstance();
//        cal1.set(Calendar.YEAR, 2012);
//        cal1.set(Calendar.MONTH, 0);
//        cal1.set(Calendar.DAY_OF_MONTH, 20);
//        machine2.setDateOfBuy(cal2.getTime());
//        machine2.setDateOfLastRevision(cal2.getTime());
//    }

    @Test
    public void testCreate(){
        Machine machine1 = new Machine();
        machine1.setName("Test1");
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.CRANE);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2011);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        machine1.setDateOfBuy(cal1.getTime());
        machine1.setDateOfLastRevision(cal1.getTime());

        machineDao.create(machine1);
        Assert.assertNotNull(machine1.getId());
        machineDao.delete(machine1);
    }

    @Test
    public void testUpdate(){
        Machine machine1 = new Machine();
        machine1.setName("Test1");
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.CRANE);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2011);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        machine1.setDateOfBuy(cal1.getTime());
        machine1.setDateOfLastRevision(cal1.getTime());

        machineDao.create(machine1);
        machine1.setName("Updated");
        machineDao.update(machine1);
        Assert.assertEquals(machineDao.findById(machine1.getId()).getName(),"Updated");
        machineDao.delete(machine1);
    }

    @Test
    public void createFindDeleteTest(){
        Machine machine1 = new Machine();
        machine1.setName("Test1");
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.CRANE);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2011);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        machine1.setDateOfBuy(cal1.getTime());
        machine1.setDateOfLastRevision(cal1.getTime());
        machineDao.create(machine1);

        Machine machine2 = new Machine();
        machine2.setName("Test2");
        machine2.setPricePerDay(BigDecimal.ONE);
        machine2.setMachineType(MachineType.LORRY);
        Calendar cal2 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2012);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        machine2.setDateOfBuy(cal2.getTime());
        machine2.setDateOfLastRevision(cal2.getTime());
        machineDao.create(machine2);

        Assert.assertEquals(machineDao.findById(machine1.getId()).getName(), "Test1");
        Assert.assertEquals(machineDao.findAllMachines().size(), 2);
        machineDao.delete(machine1);
        Assert.assertEquals(machineDao.findAllMachines().size(), 1);
        machineDao.delete(machine2);
        Assert.assertEquals(machineDao.findAllMachines().size(), 0);
    }

    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void testNullName(){
        Machine machine1 = new Machine();
        machine1.setPricePerDay(BigDecimal.TEN);
        machine1.setMachineType(MachineType.CRANE);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2011);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        machine1.setDateOfBuy(cal1.getTime());
        machine1.setDateOfLastRevision(cal1.getTime());
        machine1.setName(null);
        machineDao.create(machine1);
    }
}
