/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Rental;
import java.util.List;

/**
 *
 * @author eduard
 */
public interface RentalService {
    
    /**
     * Create new rental
     * @param rental to be created
     * @return created rental
     */
    public Rental create(Rental rental);
    
    /**
     * Update given rental
     * @param rental with values to be updated
     */
    public void update(Rental rental);
    
    /**
     * Delete given rental
     * @param rental to be delete
     */
    public void delete(Rental rental);
    
    /**
     * Find rental by given id
     * @param id rental id to be found
     * @return found rental
     */
    public Rental findRentalById(Long id);
    
     /**
     * Return all rentals.
     * @return List of rentals.
     */
    public List<Rental> findAllRentals();
}
