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
import java.util.Date;
import java.util.Iterator;
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
        
        Date from = null;
        Date to = null;

        if (rentalDTO.getDateFrom() != null && !rentalDTO.getDateFrom().trim().isEmpty()) {
            from = parser.parse(rentalDTO.getDateFrom().trim());
        }
        
        if (rentalDTO.getDateTo() != null && !rentalDTO.getDateTo().trim().isEmpty()) {
            to = parser.parse(rentalDTO.getDateTo().trim());
        }
        
        if (rentalDTO.getDateFrom() != null && !rentalDTO.getDateFrom().trim().isEmpty()) {
            from = parser.parse(rentalDTO.getDateFrom().trim());
        }
        else
        {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date from\" cannot be empty.");
            redirectAttributes.addFlashAttribute("rental", rentalDTO);
            return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        if (rentalDTO.getDateTo() != null && !rentalDTO.getDateTo().trim().isEmpty()) {
            to = parser.parse(rentalDTO.getDateTo().trim());
        }
        else
        {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date to\" cannot be empty.");
            redirectAttributes.addFlashAttribute("rental", rentalDTO);
            return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        if(from != null && to != null && from.after(to))
        {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date to\" cannot precede \"Date from\".");
            return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        // checks if rental desn't overlap with another rental
        List<RentalDTO> listOfRentals = rentalFacade.findAllRentals();
        
        Iterator<RentalDTO> it = listOfRentals.iterator();
        if(!it.hasNext()) return "redirect:new";
        while(it.hasNext())
        {
            RentalDTO existingRental = it.next();
            if(existingRental.getId().equals(rental.getId()) || !existingRental.getMachine().getId().equals(rental.getMachine().getId()))
            {
                System.err.println("Přeskakuje se výpůjčka " + existingRental.getId());
                continue;
            }
            
            if( from.before(existingRental.getDateTo()) && existingRental.getDateFrom().before(to) )
            {
                redirectAttributes.addFlashAttribute("alert_danger", "Rentals cannot overal each other.");
                redirectAttributes.addFlashAttribute("rental", rentalDTO);
                return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
            }
        }
        
        if(from != null) rental.setDateFrom(from);
        if(to != null) rental.setDateTo(to);
        
        int price = 0;
        if (rentalDTO.getPrice() != null) {
            price = rentalDTO.getPrice();
            if(price < 0)
            {
                redirectAttributes.addFlashAttribute("alert_danger", "Price can't be negative.");
                return "redirect:" + uriBuilder.path("/rental/edit/{id}").buildAndExpand(id).encode().toUriString();
            }
            rental.setPrice(price);
        }

        rentalFacade.updateRental(rental);
        model.addAttribute("rental", rental);

        redirectAttributes.addFlashAttribute("alert_success", "Rental details saved successfully.");
        return "redirect:/rental/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRental(@ModelAttribute("rental") RentalCreateDTO rentalDTO, Model model) {
        if(rentalDTO == null)
        {
            model.addAttribute("rental", new RentalCreateDTO());
        }
        else
        {
            model.addAttribute("rental", rentalDTO);
        }
        return "rental/new";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String submitNew(@ModelAttribute("rental") RentalCreateDTO rentalDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) throws ParseException {
        RentalDTO rental = new RentalDTO();
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        
        Date from = null;
        Date to = null;

        if (rentalDTO.getDateFrom() != null && !rentalDTO.getDateFrom().trim().isEmpty()) {
            from = parser.parse(rentalDTO.getDateFrom().trim());
        }
        else
        {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date from\" cannot be empty.");
            redirectAttributes.addFlashAttribute("rental", rentalDTO);
            return "redirect:new";
        }
        
        if (rentalDTO.getDateTo() != null && !rentalDTO.getDateTo().trim().isEmpty()) {
            to = parser.parse(rentalDTO.getDateTo().trim());
        }
        else
        {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date to\" cannot be empty.");
            redirectAttributes.addFlashAttribute("rental", rentalDTO);
            return "redirect:new";
        }
        
        // checks if "date to" doesn't precede "date from"
        if(from != null && to != null && from.after(to))
        {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date to\" cannot precede \"Date from\".");
            redirectAttributes.addFlashAttribute("rental", rentalDTO);
            return "redirect:new";
        }
        
        if (rentalDTO.getMachine() != null && !rentalDTO.getMachine().trim().isEmpty()) {
            rental.setMachine(machineFacade.findById(new Long(rentalDTO.getMachine().trim())));
        }
        
        if (rentalDTO.getPrice() != null) {
            int price = rentalDTO.getPrice();
            
            if(price < 0)
            {
                redirectAttributes.addFlashAttribute("alert_danger", "Price can't be negative.");
                redirectAttributes.addFlashAttribute("rental", rentalDTO);
                return "redirect:new";
            }
            
            rental.setPrice(price);
        }
        
        // checks if rental desn't overlap with another rental
        List<RentalDTO> listOfRentals = rentalFacade.findAllRentals();
        
        Iterator<RentalDTO> it = listOfRentals.iterator();
        if(!it.hasNext()) return "redirect:new";
        while(it.hasNext())
        {
            RentalDTO existingRental = it.next();
            if(!existingRental.getMachine().getId().equals(rental.getMachine().getId()))
            {
                System.err.println("Přeskakuje se výpůjčka " + existingRental.getId());
                continue;
            }
            
            if( from.before(existingRental.getDateTo()) && existingRental.getDateFrom().before(to) )
            {
                redirectAttributes.addFlashAttribute("alert_danger", "Rentals cannot overal each other.");
                redirectAttributes.addFlashAttribute("rental", rentalDTO);
                return "redirect:new";
            }
        }
        
        // saves the rental dates
        if(from != null) rental.setDateFrom(from);
        if(to != null) rental.setDateTo(to);
        
        if (rentalDTO.getUser() != null && !rentalDTO.getUser().trim().isEmpty()) {
            rental.setUser(userFacade.findByEmail(rentalDTO.getUser().trim()));
        }

        rentalFacade.createRental(rental);

        redirectAttributes.addFlashAttribute("alert_success", "Rental details saved successfully.");
        return "redirect:list";
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