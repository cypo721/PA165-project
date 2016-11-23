package service.facade;

import dto.MachineDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import facade.MachineFacade;
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

    @Override
    public void createMachine() {

    }

    @Override
    public void updateMachine() {

    }

    @Override
    public void deleteMachine() {

    }

    @Override
    public MachineDTO findById() {
        return null;
    }

    @Override
    public List<MachineDTO> findAllMachines() {
        return null;
    }
}
