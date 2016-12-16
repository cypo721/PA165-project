/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Rental;

import java.util.List;

/**
 *
 * @author Marek Bohm
 */
public interface RentalDao {
    
    /**
     * Creates persistent representation of rental in database.
     * @param rental rental to persist
     */    
    public void create(Rental rental);
   
    /**
     * Update rental in database.
     * @param rental rental to update.
     */
    public void update(Rental rental);
  
    /**
     * Delete rental from database.
     * @param rental Rental to delete.
     */
    public void delete(Rental rental);
   
    /**
     * Find rental database.
     * @param id rental id to be found.
     * @return Rental with given id.
     */
    public Rental findById(Long id);
   
    /**
     * Method for retrieving all rentals from database.
     * @return All rentals from database.
     */
    public List<Rental> findAllRentals();
}
