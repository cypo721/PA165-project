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
     * 
     * @param rental 
     */    
    public void create(Rental rental);
   
    /**
     * 
     * @param rental 
     */
    public void update(Rental rental);
  
    /**
     * 
     * @param rental 
     */
    public void delete(Rental rental);
   
    /**
     * 
     * @param id
     * @return 
     */
    public Rental findById(Long id);
   
    /**
     * 
     * @return 
     */
    public List<Rental> findAllRentals();
}
