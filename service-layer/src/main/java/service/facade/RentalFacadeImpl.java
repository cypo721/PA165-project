/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.facade;

import dto.RentalDTO;
import entity.Rental;
import facade.RentalFacade;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import service.RentalService;
/**
 *
 * @author eduard
 */
public class RentalFacadeImpl implements RentalFacade {
    @Inject
    private RentalService rentalService;
    
    @Inject
    private Mapper dozer;


    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        Validate.isTrue(rentalDTO.getId() == null);
        
        Rental rental = convert(rentalDTO);
        Rental saved = rentalService.create(rental);
        
        return convert(saved);
    }

    @Override
    public RentalDTO updateRental(RentalDTO rentalDTO) {
        Validate.notNull(rentalDTO.getId());
        
        Rental entity = convert(rentalDTO);
        Rental updated = rentalService.update(entity);
        
        return convert(updated);
    }


    @Override
    public RentalDTO findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RentalDTO> findAllRentals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Rental convert(RentalDTO dto) {
        return dozer.map(dto, Rental.class);
    }
    
    private RentalDTO convert(Rental entity) {
        return dozer.map(entity, RentalDTO.class);
    }

}
