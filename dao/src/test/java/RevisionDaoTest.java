import config.PersistenceSampleApplicationContext;
import dao.MachineDao;
import dao.RevisionDao;
import dao.UserDao;
import entity.Machine;
import entity.Revision;
import entity.User;
import enums.MachineType;
import enums.PersonType;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by pato on 31.10.2016.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class RevisionDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RevisionDao revisionDao;
    @Autowired
    private MachineDao machineDao;
    @Autowired
    private UserDao userDao;
    private User u;
    private Machine machine1;

    @BeforeClass
    public void beforeClass(){
        machine1 = new Machine();
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

        u = new User();
        u.setGivenName("Bla");
        u.setPasswordHash("asdsf");
        u.setEmail("xy@mail.com");
        u.setPersonType(PersonType.NATURAL);
        u.setSurname("Alb");
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2012);
        cal2.set(Calendar.MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        u.setJoinedDate(cal1.getTime());
        u.setPhone("0915702236");
        u.setRole(Role.EMPLOYEE);
        userDao.create(u);
    }

    @Test
    public void testCreate(){
        Revision r = getRevision();
        r.setMachine(machine1);
        r.setUser(u);

        revisionDao.create(r);

        Assert.assertNotNull(r.getId());
        Assert.assertEquals(u, r.getUser());
        Assert.assertEquals(machine1, r.getMachine());
    }

    @Test
    public void testfindById(){
        Revision r = getRevision();
        r.setMachine(machine1);
        r.setUser(u);

        revisionDao.create(r);
        Assert.assertEquals(r.getUser().getEmail(), revisionDao.findById(r.getId()).getUser().getEmail());
        Assert.assertEquals(r.getMachine().getName(), revisionDao.findById(r.getId()).getMachine().getName());
    }

    @Test
    public void testUpdate(){
        Revision r = getRevision();
        r.setMachine(machine1);
        r.setUser(u);
        revisionDao.create(r);

        r.setInfo("Machine doesn't start.");
        r.setIsFunctionable(false);
        revisionDao.update(r);
        Assert.assertEquals("Machine doesn't start.", revisionDao.findById(r.getId()).getInfo());
        Assert.assertEquals(false, revisionDao.findById(r.getId()).getIsFunctionable());
    }



    @Test
    public void createFindDeleteTest(){
        Revision r = getRevision();
        r.setMachine(machine1);
        r.setUser(u);

        revisionDao.create(r);
        Revision r2 = getRevision();
        r2.setMachine(machine1);
        r2.setUser(u);

        revisionDao.create(r2);
        Assert.assertEquals(revisionDao.findById(r.getId()).getUser(), u);
        Assert.assertEquals(revisionDao.findAllRevisions().size(), 2);
        revisionDao.delete(r2);
        Assert.assertEquals(revisionDao.findAllRevisions().size(), 1);
        revisionDao.delete(r);
        Assert.assertEquals(revisionDao.findAllRevisions().size(), 0);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testNullUser(){
        Revision r = getRevision();
        r.setMachine(machine1);

        revisionDao.create(r);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testNullMachine(){
        Revision r = getRevision();
        r.setUser(u);

        revisionDao.create(r);
    }

    private Revision getRevision() {
        Revision r = new Revision();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2012);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        r.setDateOfRevision(cal1.getTime());
        r.setInfo("Machine was revisioned. No problems found.");
        r.setIsFunctionable(true);

        return r;
    }

}
