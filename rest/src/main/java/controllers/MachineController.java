package controllers;

import dto.MachineDTO;
import facade.MachineFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pato on 13.12.2016.
 */
@RestController
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    private MachineFacade machineFacade;

    @RequestMapping(method = RequestMethod.GET)
    public List<MachineDTO> getAllMachines() {
        return machineFacade.findAllMachines();
    }
}
