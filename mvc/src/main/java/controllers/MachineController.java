package controllers;

import facade.MachineFacade;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by pato on 14.12.2016.
 */
@Controller
@RequestMapping("/machine")
public class MachineController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineFacade machineFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model) {
        logger.info("Showing all additional services");
        model.addAttribute("machines", machineFacade.findAllMachines());
        return "machine/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
        logger.info("Deleting machine with id: {}", id);
        machineFacade.deleteMachine(id);
        return "redirect:" + uriBuilder.path("/machine/list").toUriString();
    }
}
