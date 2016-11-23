/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RentalDao;
import entity.Rental;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;


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
    public Rental update(Rental rental) {
        Validate.notNull(rental.getId());
        rentalDao.create(rental);
        return rental;
    }

    @Override
    public Rental findRentalById(Long id) {
         return rentalDao.findById(id);
    }

    @Override
    public List<Rental> findAllRentals() {
        return rentalDao.findAllRentals();
    }
    
}
