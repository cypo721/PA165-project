/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.RevisionDTO;
import facade.RevisionFacade;
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
 * @author eduard
 */
@Controller
@RequestMapping("/revision")
public class RevisionController {
    @Autowired
    private RevisionFacade revisionFacade;
 
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model)
    {
        model.addAttribute("revisions", revisionFacade.findAllRevisions());
        return "revision/list";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("revision", revisionFacade.findById(id));
        return "revision/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String submitUpdate(@PathVariable long id,
                             @ModelAttribute("revision") RevisionDTO revisionDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) {
        RevisionDTO revision = revisionFacade.findById(id);

        if (revisionDTO.getDateOfRevision()!= null) {
            revision.setDateOfRevision(revisionDTO.getDateOfRevision());
        }
       
        if (revisionDTO.getId() != null) {
            revision.setId(revisionDTO.getId());
        }
        
        if (revisionDTO.getMachine() != null) {
            revision.setMachine(revisionDTO.getMachine());
        }
        
        if (revisionDTO.getInfo()!= null) {
            revision.setInfo(revisionDTO.getInfo());
        }
        
        if (revisionDTO.getUser() != null) {
            revision.setUser(revisionDTO.getUser());
        }

        revisionFacade.updateRevision(revision);
        model.addAttribute("revision", revision);

        redirectAttributes.addFlashAttribute("alert_success", "Revision details saved successfully.");
        return "redirect:" + uriBuilder.path("/revision/edit/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}
