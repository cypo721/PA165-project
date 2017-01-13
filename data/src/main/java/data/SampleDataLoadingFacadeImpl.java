package data;

import entity.*;
import enums.MachineType;
import enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.MachineService;
import service.RentalService;
import service.RevisionService;
import service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import java.util.Date;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    public static final String JPEG = "image/jpeg";

    @Autowired
    private MachineService machineService;
    @Autowired
    private RevisionService revisionService;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private UserService userService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Machine crane = machine("Crane", toDate(2016, 12, 12));
        Machine m = machine("Best", toDate(2016, 12, 24));
        Machine m2 = machine("Lorry", toDate(2015, 12, 12));

        User u = user("123456", "Luna", "Lovegood", "luna@hog.uk", "800123456", new Date(), "C");
        User u1 = user("123456", "Sme", "taky", "sme@taky.uk", "800223456", new Date(), "C");
        User u3 = user("pwd", "Laszlo", "Admin", "admin@admin.sk", "904355662", new Date(), "A");
        User u4 = user("pwd", "Fero", "Customer", "customer@customer.sk", "903030132", new Date(), "C");
        User e = user("pwd", "Jozo", "Employee", "employee@employee.sk", "903030132", new Date(), "E");

        Revision r = revision(new Date(), m, u);
        Revision r1 = revision(new Date(), m2 , u);

        Rental rent = rental(u, m);
        log.info("loaded date of application");
    }

//    private static Date daysBeforeNow(int days) {
//        return Date.from(ZonedDateTime.now().minusDays(days).toInstant());
//    }
//
//    private static Date toDate(int year, int month, int day) {
//        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
//    }

    private Rental rental(User u, Machine m){
        Rental r = new Rental();
        r.setPrice(100);
        r.setUser(u);
        r.setMachine(m);
        r.setDateFrom(toDate(2017, 1 , 10));
        r.setDateTo(toDate(2017, 2 , 10));
        rentalService.create(r);

        return r;
    }

    private Machine machine(String name, Date date) {
        Machine m = new Machine();
        m.setName(name);
        m.setDateOfBuy(date);
        m.setMachineType(MachineType.CRANE);
        m.setPricePerDay(BigDecimal.TEN);

        machineService.create(m);
        return m;
    }
    
    private static Date toDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Revision revision(Date date, Machine m, User u) {
        Revision r = new Revision();
        
        r.setMachine(m);
        r.setUser(u);    
        r.setInfo("blabla");
        r.setIsFunctionable(true);
        r.setDateOfRevision(date);
        revisionService.create(r);
        
        return r;
    }

    private User user(String password, String givenName, String surname, String email, String phone, Date joined, String role) {
        User u = new User();
        u.setGivenName(givenName);
        u.setPasswordHash("asdasd");
        u.setSurname(surname);
        u.setEmail(email);
        u.setPhone(phone);
        u.setJoinedDate(joined);
        if(role == "A") u.setRole(Role.ADMIN);
        if(role == "C") u.setRole(Role.CUSTOMER);
        if(role == "E") u.setRole(Role.EMPLOYEE);

        userService.createUser(u, password);
        return u;
    }
}
