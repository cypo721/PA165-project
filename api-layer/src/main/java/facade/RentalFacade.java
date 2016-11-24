/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.RentalDTO;
import java.util.List;
/**
 *
 * @author eduard
 */
public interface RentalFacade {
    public Long createRental(RentalDTO rentalDTO);

    public void updateRental(RentalDTO rentalDTO);
    
    public void deleteRental(Long id);
   
    public RentalDTO findById(Long id);
  
    public List<RentalDTO> findAllRentals();
}
