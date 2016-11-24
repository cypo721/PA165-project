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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BeanMappingService;
import service.RentalService;
import static service.facade.MachineFacadeImpl.log;
/**
 *
 * @author eduard
 */
@Service
@Transactional
public class RentalFacadeImpl implements RentalFacade {
    @Inject
    private RentalService rentalService;
    
      @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createRental(RentalDTO rentalDTO) {
        Validate.isTrue(rentalDTO.getId() == null);
        
        log.debug("Trying to create rentalDTO {}", rentalDTO);
        Rental rental = beanMappingService.mapTo(rentalDTO, Rental.class);
        Rental saved = rentalService.create(rental);
        
        return rental.getId();
    }

    @Override
    public void updateRental(RentalDTO rentalDTO) {
        Validate.notNull(rentalDTO.getId());
        
        log.debug("Trying to update rentalDTO {}", rentalDTO);
        Rental mappedRental = beanMappingService.mapTo(rentalDTO, Rental.class);
        rentalService.update(mappedRental);    
    }

        @Override
    public void deleteRental(Long id) {
        
        log.debug("Trying to delete machine with id {}", id);
        Rental rental = new Rental(); //todo
        rental.setId(id);
        rentalService.delete(rental);
    }

    @Override
    public RentalDTO findById(Long id) {
        log.debug("Trying to find machine with id {}", id);
        return beanMappingService.mapTo(rentalService.findRentalById(id), RentalDTO.class);
    }

    @Override
    public List<RentalDTO> findAllRentals() {
        log.debug("Trying to get all rentals with id");
        return beanMappingService.mapTo(rentalService.findAllRentals(), RentalDTO.class);
    }


    

}
