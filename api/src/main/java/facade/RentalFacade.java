/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.MachineDTO;
import dto.RentalDTO;

import java.util.List;

/**
 *
 * @author eduard
 */
public interface RentalFacade {
    
    /**
     * Create new rental
     * @param m rental to be created
     * @return id of new rental
     */
    public Long createRental(RentalDTO rentalDTO);

    /**
     * Update given rental
     * @param m rental to be updated
     */
    public void updateRental(RentalDTO rentalDTO);
    
    /**
     * Delete rental with given id
     * @param id of rental to be deleted
     */
    public void deleteRental(Long id);
   
    /**
     * Find rental with given id
     * @param id of rental to be found
     * @return rental with given id
     */
    public RentalDTO findById(Long id);
  
    /**
     * Find all rentals.
     * @return List of all rentals.
     */
    public List<RentalDTO> findAllRentals();
    
    /**
     * Return all machines which are rented in current week (Monday-Sunday)
     * @return all rented machines in current week
     */
    public List<MachineDTO> findAllMachinesRentedCurrentWeek();
}
