import config.PersistenceSampleApplicationContext;
import dao.MachineDao;
import entity.Machine;
import enums.MachineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by pato on 30.10.2016.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class MachineDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    MachineDao machineDao;

    @Test
    public void testCreate(){
        Machine m = getMachine();
        machineDao.create(m);

        Assert.assertNotNull(m.getId());
        Assert.assertEquals(BigDecimal.TEN, m.getPricePerDay());
    }

    @Test
    public void testfindById(){
        Machine m = getMachine();
        machineDao.create(m);
        Assert.assertEquals(m.getName(), machineDao.findById(m.getId()).getName());
    }

    @Test
    public void testUpdate(){
        Machine m = getMachine();
        machineDao.create(m);
        m.setName("Updated");
        machineDao.update(m);
        Assert.assertEquals("Updated", machineDao.findById(m.getId()).getName());
    }



    @Test
    public void createFindDeleteTest(){
        Machine m = getMachine();
        machineDao.create(m);
        Machine m2 = getMachine();
        machineDao.create(m2);
        Assert.assertEquals(machineDao.findById(m.getId()).getName(), "Test1");
        Assert.assertEquals(machineDao.findAllMachines().size(), 2);
        machineDao.delete(m2);
        Assert.assertEquals(machineDao.findAllMachines().size(), 1);
        machineDao.delete(m);
        Assert.assertEquals(machineDao.findAllMachines().size(), 0);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testNullName(){
        Machine m = getMachine();
        m.setName(null);
        machineDao.create(m);
    }

    //@Test(expectedExceptions = org.springframework.transaction.TransactionSystemException.class)
    public void testNullDateOfBuy(){
        Machine m = getMachine();
        machineDao.create(m);
        m.setDateOfBuy(null);

        machineDao.update(m);
    }

    private Machine getMachine() {
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

        return machine1;
    }
}
