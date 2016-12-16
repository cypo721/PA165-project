/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.RentalDTO;
import facade.RentalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Václav Zouzalík
 */
@Controller
@RequestMapping("/rental")
public class RentalController {
    @Autowired
    private RentalFacade rentalFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model)
    {
        model.addAttribute("rentals", rentalFacade.findAllRentals());
        return "rental/list";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("rental", rentalFacade.findById(id));
        return "rental/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String submitEdit(@PathVariable long id,
                             @ModelAttribute("rental") RentalDTO rentalDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) {
        RentalDTO rental = rentalFacade.findById(id);

        if (rentalDTO.getDateFrom() != null) {
            rental.setDateFrom(rentalDTO.getDateFrom());
        }
        
        if (rentalDTO.getDateTo() != null) {
            rental.setDateTo(rentalDTO.getDateTo());
        }

        if (rentalDTO.getId() != null) {
            rental.setId(rentalDTO.getId());
        }
        
        if (rentalDTO.getMachine() != null) {
            rental.setMachine(rentalDTO.getMachine());
        }
        
        if (rentalDTO.getPrice() != null) {
            rental.setPrice(rentalDTO.getPrice());
        }
        
        if (rentalDTO.getUser() != null) {
            rental.setUser(rentalDTO.getUser());
        }

        rentalFacade.updateRental(rental);
        model.addAttribute("rental", rental);

        redirectAttributes.addFlashAttribute("alert_success", "Rental details saved successfully.");
        return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder)
    {
        rentalFacade.deleteRental(id);
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
}
