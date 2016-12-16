package service.facade;

import dto.MachineDTO;
import entity.Machine;
import facade.MachineFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BeanMappingService;
import service.MachineService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
@Service
@Transactional
public class MachineFacadeImpl implements MachineFacade {

    final static Logger log = LoggerFactory.getLogger(MachineFacadeImpl.class);

    @Inject
    private MachineService machineService;

    @Inject
    private BeanMappingService beanMappingService;


    @Override
    public Long createMachine(MachineDTO m) {
        log.debug("Trying to create machineDto {}", m);
        Machine mappedMachine = beanMappingService.mapTo(m, Machine.class);
        Machine newMachine = machineService.create(mappedMachine);
        return newMachine.getId();
    }

    @Override
    public void updateMachine(MachineDTO m) {
        log.debug("Trying to update machineDto {}", m);
        Machine mappedMachine = beanMappingService.mapTo(m, Machine.class);
        machineService.update(mappedMachine);
    }

    @Override
    public void deleteMachine(Long id) {
        log.debug("Trying to delete machine with id {}", id);
        Machine machine = new Machine(); //todo
        machine.setId(id);
        machineService.delete(machine);
    }

    @Override
    public MachineDTO findById(Long id) {
        log.debug("Trying to find machine with id {}", id);
        return beanMappingService.mapTo(machineService.findById(id), MachineDTO.class);
    }

    @Override
    public List<MachineDTO> findAllMachines() {
        log.debug("Trying to get all machines");
        return beanMappingService.mapTo(machineService.findAllMachines(), MachineDTO.class);
    }

    @Override
    public List<MachineDTO> getMachinesInLastYearWithoutRevision() {
        log.debug("Trying to get all unrevisioned machines in last year.");
        return beanMappingService.mapTo(machineService.getMachinesInLastYearWithoutRevision(), MachineDTO.class);
    }
}
