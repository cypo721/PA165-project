/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.*;
import facade.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
    
    @Autowired
    private MachineFacade machineFacade;
    
    @Autowired
    private UserFacade userFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model)
    {
        model.addAttribute("rentals", rentalFacade.findAllRentals());
        return "rental/list";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) throws ParseException {
        RentalDTO rental = rentalFacade.findById(id);
        RentalCreateDTO rentalDTO = new RentalCreateDTO();
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        
        rentalDTO.setDateFrom(parser.format(rental.getDateFrom()));
        rentalDTO.setDateTo(parser.format(rental.getDateTo()));
        rentalDTO.setPrice(rental.getPrice());
        
        model.addAttribute("rental", rentalDTO);

        return "rental/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String submitEdit(@PathVariable long id,
                             @ModelAttribute("rental") RentalCreateDTO rentalDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) throws ParseException {
        RentalDTO rental = rentalFacade.findById(id);

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        if (rentalDTO.getDateFrom() != null && !rentalDTO.getDateFrom().trim().isEmpty()) {
            rental.setDateFrom(parser.parse(rentalDTO.getDateFrom().trim()));
        }
        
        if (rentalDTO.getDateTo() != null && !rentalDTO.getDateTo().trim().isEmpty()) {
            rental.setDateTo(parser.parse(rentalDTO.getDateTo().trim()));

        }
        
        if (rentalDTO.getPrice() != null) {
            rental.setPrice(rentalDTO.getPrice());
        }


        rentalFacade.updateRental(rental);
        model.addAttribute("rental", rental);

        redirectAttributes.addFlashAttribute("alert_success", "Rental details saved successfully.");
        return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRental(Model model) {
        model.addAttribute("rental", new RentalCreateDTO());
        return "rental/new";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String submitNew(@ModelAttribute("rental") RentalCreateDTO rentalDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) throws ParseException {
        RentalDTO rental = new RentalDTO();
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        if (rentalDTO.getDateFrom() != null && !rentalDTO.getDateFrom().trim().isEmpty()) {
            rental.setDateFrom(parser.parse(rentalDTO.getDateFrom().trim()));
        }
        
        if (rentalDTO.getDateTo() != null && !rentalDTO.getDateTo().trim().isEmpty()) {
            rental.setDateTo(parser.parse(rentalDTO.getDateTo().trim()));

        }
        
        if (rentalDTO.getMachine() != null && !rentalDTO.getMachine().trim().isEmpty()) {
            rental.setMachine(machineFacade.findById(new Long(rentalDTO.getMachine().trim())));
        }
        
        if (rentalDTO.getPrice() != null) {
            rental.setPrice(rentalDTO.getPrice());
        }
        
        if (rentalDTO.getUser() != null && !rentalDTO.getUser().trim().isEmpty()) {
            rental.setUser(userFacade.findByEmail(rentalDTO.getUser().trim()));
        }

        Long newId = rentalFacade.createRental(rental);
        model.addAttribute("rental", rental);

        redirectAttributes.addFlashAttribute("alert_success", "Rental details saved successfully.");
        return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(newId).encode().toUriString();

    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes)
    {
        rentalFacade.deleteRental(id);
        redirectAttributes.addFlashAttribute("alert_success", "Rental \"" + id + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
    
    @ModelAttribute("machines")
    public List<MachineDTO> machines() {
        return machineFacade.findAllMachines();
    }
    
    @ModelAttribute("users")
    public Collection<UserDTO> users() {
        return userFacade.getAllUsers();
    }
}
