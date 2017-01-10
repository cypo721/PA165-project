package config.controllers;

import config.ApiUris;
import dto.MachineDTO;
import facade.MachineFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pato on 16.12.2016.
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_MACHINES)
public class MachineController {

    final static Logger logger = LoggerFactory.getLogger(MachineController.class);

    @Inject
    private MachineFacade machineFacade;

    /**
     * Get list of Machines curl -i -X GET
     * http://localhost:8080/rest/machines
     *
     * @return list of MachineDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MachineDTO> getMachines() {
        logger.debug("rest getMachines()");
        return machineFacade.findAllMachines();
    }

    /**
     *
     * Get Machine by identifier id curl -i -X GET
     * http://localhost:8080/rest/machines/1
     *
     * @param id identifier for a machine
     * @return MachineDTO
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MachineDTO getMachine(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getMachine({})", id);
        MachineDTO machineDTO = machineFacade.findById(id);
        if (machineDTO != null) {
            return machineDTO;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Delete one machine by id curl -i -X DELETE
     * http://localhost:8080/rest/machines/1
     *
     * @param id identifier for a machine
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteMachine(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteMachine({})", id);
        try {
            machineFacade.deleteMachine(id);
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Create a new machine by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data
     * '{"name":"test", "dateOfBuy":"2016-12-12","machineType":"CRANE","pricePerDay":"200",
     *  "dateOfLastRevision":"2016-12-12"}'
     * http://localhost:8080/rest/machines/create
     *
     * @param machine MachineDTO with required fields for creation
     * @return the created machineDTO
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MachineDTO createMachine(@RequestBody MachineDTO machine) throws Exception {

        logger.debug("rest createMachine()");

        try {
            Long id = machineFacade.createMachine(machine);
            return machineFacade.findById(id);
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }

}
