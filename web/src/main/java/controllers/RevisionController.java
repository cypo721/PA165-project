/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.MachineDTO;
import dto.RevisionCreateDTO;
import dto.RevisionDTO;
import dto.UserDTO;
import facade.MachineFacade;
import facade.RevisionFacade;
import facade.UserFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RevisionFacade revisionFacade;
    
    @Autowired
    private MachineFacade machineFacade;
    
    @Autowired
    private UserFacade userFacade;

    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll(Model model) {
        logger.info("Showing all additional services");
        model.addAttribute("revisions", revisionFacade.findAllRevisions());
        return "revision/list";
    }
   
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRevision(Model model) {
        model.addAttribute("revision", new RevisionCreateDTO());
        return "revision/new";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String submitNew(@ModelAttribute("revision") RevisionCreateDTO revisionDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) throws ParseException {
        RevisionDTO revision = new RevisionDTO();
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        if (revisionDTO.getDateOfRevision() != null && !revisionDTO.getDateOfRevision().trim().isEmpty()) {
            revision.setDateOfRevision(parser.parse(revisionDTO.getDateOfRevision().trim()));
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Date of revision\" cannot be empty.");
            redirectAttributes.addFlashAttribute("revision", revisionDTO);
            return "redirect:new";
        }
        
        if (revisionDTO.getInfo() != null && !revisionDTO.getInfo().trim().isEmpty()) {
            revision.setInfo(revisionDTO.getInfo());
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Info\" cannot be empty.");
            redirectAttributes.addFlashAttribute("revision", revisionDTO);
            return "redirect:new";
        }
        
        if (revisionDTO.getMachine() != null && !revisionDTO.getMachine().trim().isEmpty()) {
            revision.setMachine(machineFacade.findById(new Long(revisionDTO.getMachine().trim())));
        }
                
        if (revisionDTO.getUser() != null && !revisionDTO.getUser().trim().isEmpty()) {
            revision.setUser(userFacade.findByEmail(revisionDTO.getUser().trim()));
        }

        Long newId = revisionFacade.createRevision(revision);
        model.addAttribute("revision", revision);

        redirectAttributes.addFlashAttribute("alert_success", "Revision was successfully added.");
        return "redirect:";

    }
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id) throws ParseException {
        RevisionDTO revision = revisionFacade.findById(id);
        RevisionCreateDTO revisionDTO = new RevisionCreateDTO();
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        
        revisionDTO.setDateOfRevision(parser.format(revision.getDateOfRevision()));
        revisionDTO.setInfo(revision.getInfo());
        
        model.addAttribute("revision", revisionDTO);

        return "revision/edit";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String submitEdit(@PathVariable long id,
                             @ModelAttribute("rental") RevisionCreateDTO revisionDTO,
                             Model model, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) throws ParseException {
        RevisionDTO revision = revisionFacade.findById(id);

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        
        if (revisionDTO.getDateOfRevision() != null && !revisionDTO.getDateOfRevision().trim().isEmpty()) {
            revision.setDateOfRevision(parser.parse(revisionDTO.getDateOfRevision().trim()));
        }
        
        if (revisionDTO.getInfo() != null && !revisionDTO.getInfo().trim().isEmpty()) {
            revision.setInfo(revisionDTO.getInfo());
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "\"Info\" cannot be empty.");
            redirectAttributes.addFlashAttribute("revision", revisionDTO);
            return "redirect:" + uriBuilder.path("/revision/edit/{id}").buildAndExpand(id).encode().toUriString();   
        }
        
        if (revisionDTO.getMachine() != null && !revisionDTO.getMachine().trim().isEmpty()) {
            revision.setMachine(machineFacade.findById(new Long(revisionDTO.getMachine().trim())));
        }
                
        if (revisionDTO.getUser() != null && !revisionDTO.getUser().trim().isEmpty()) {
            revision.setUser(userFacade.findByEmail(revisionDTO.getUser().trim()));
        }

        revisionFacade.updateRevision(revision);
        model.addAttribute("revision", revision);

        redirectAttributes.addFlashAttribute("alert_success", "Revision details saved successfully.");
        return "redirect:/revision/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes ) {
        RevisionDTO revision = revisionFacade.findById(id);
        revisionFacade.deleteRevision(id);
        logger.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Revision with id: \"" + revision.getId()+ "\" was deleted.");
        return "redirect:" + uriBuilder.path("/revision/").toUriString();
    }
    
    @InitBinder
    protected void dataBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
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