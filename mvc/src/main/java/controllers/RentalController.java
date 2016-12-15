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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder)
    {
        rentalFacade.deleteRental(id);
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
}
