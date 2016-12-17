/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.MachineDTO;
import dto.RevisionDTO;
import dto.UserDTO;
import facade.MachineFacade;
import facade.RevisionFacade;
import facade.UserFacade;
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
    
    @GetMapping("/new")
    public String newRevision(Model model) {
        model.addAttribute("revision", new RevisionDTO());
        return "revision/edit";
    }

    @GetMapping("/edit/{id}")
    public String editRevision(@PathVariable Long id, Model model) {
        model.addAttribute("revision", revisionFacade.findById(id));
        return "revision/edit";
    }
    
    @PostMapping("/save")
    public String saveRevision(@Valid @ModelAttribute("revision") RevisionDTO dto,
                                        BindingResult bindingResult,
                                        Model model,
                                        RedirectAttributes redirectAttributes,
                                        UriComponentsBuilder uriBuilder) {
        logger.info("Saving revisionDTO: {}", dto);
        if (bindingResult.hasErrors()) {
            return "revision/edit";
        }
        if (dto.getId() != null) {
            revisionFacade.updateRevision(dto);
        }
        else {
            revisionFacade.updateRevision(dto);
        }
        return "redirect:" + uriBuilder.path("/revision/").toUriString();
    }

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
        logger.debug("machines()");
        return machineFacade.findAllMachines();
    }
    
    @ModelAttribute("users")
    public Collection<UserDTO> users() {
        logger.debug("users()");
        return userFacade.getAllUsers();
    }
}