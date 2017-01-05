/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RentalDao;
import entity.Machine;
import entity.Rental;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author eduard
 */
@Service
public class RentalServiceImpl implements RentalService {

    @Inject
    private RentalDao rentalDao;
 
    @Override
    public Rental create(Rental rental) {
        Validate.isTrue(rental.getId() == null);
        rentalDao.create(rental);
        return rental;
    }

    @Override
    public void update(Rental rental) {
        Validate.notNull(rental.getId());
        rentalDao.update(rental);
    }
    
    @Override
    public void delete(Rental rental) {
        rentalDao.delete(rental);
    }

    @Override
    public Rental findRentalById(Long id) {
         return rentalDao.findById(id);
    }

    @Override
    public List<Rental> findAllRentals() {
        return rentalDao.findAllRentals();
    }
   
    @Override
    public List<Machine> findAllMachinesRentedCurrentWeek(){
        List<Rental> rentals = findAllRentals();
        List<Machine> rentedMachines = new ArrayList<>();
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        
        for(int i = 0; i < rentals.size(); i++){
            Rental r = rentals.get(i);
            if( (r.getDateFrom().after(c1.getTime()) && r.getDateTo().after(c2.getTime())) || (r.getDateTo().after(c1.getTime()) && r.getDateTo().before(c2.getTime()))) {
                rentedMachines.add(r.getMachine());
            }
        }
        
        return rentedMachines;
    }
    
}
