package controllers;

import dto.MachineDTO;
import enums.MachineType;
import facade.MachineFacade;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pato on 14.12.2016.
 */
@Controller
@RequestMapping("/machine")
public class MachineController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineFacade machineFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll(Model model) {
        logger.info("Showing all additional services");
        model.addAttribute("machines", machineFacade.findAllMachines());
        return "machine/list";
    }


    @GetMapping("/new")
    public String newMachine(Model model) {
        model.addAttribute("machine", new MachineDTO());
        return "machine/edit";
    }

    @GetMapping("/edit/{id}")
    public String editMachine(@PathVariable Long id, Model model) {
        model.addAttribute("machine", machineFacade.findById(id));
        return "machine/edit";
    }

    @PostMapping("/save")
    public String saveMachine(@Valid @ModelAttribute("machine") MachineDTO dto,
                                        BindingResult bindingResult,
                                        Model model,
                                        RedirectAttributes redirectAttributes,
                                        UriComponentsBuilder uriBuilder) {
        logger.info("Saving machineDTO: {}", dto);
        if (bindingResult.hasErrors()) {
            return "machine/edit";
        }
        if (dto.getId() != null) {
            machineFacade.updateMachine(dto);
        }
        else {
            machineFacade.createMachine(dto);
        }
        return "redirect:" + uriBuilder.path("/machine/").toUriString();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes ) {
        MachineDTO machine = machineFacade.findById(id);
        machineFacade.deleteMachine(id);
        logger.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Product \"" + machine.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/machine/").toUriString();
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
    }


    @ModelAttribute("types")
    public MachineType[] types() {
        logger.debug("types()");
        return MachineType.values();
    }
}
