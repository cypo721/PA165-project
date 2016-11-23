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
    public RentalDTO createRental(RentalDTO rentalDTO);

    public RentalDTO updateRental(RentalDTO rentalDTO);
   
    public RentalDTO findById(Long id);
  
    public List<RentalDTO> findAllRentals();
}
