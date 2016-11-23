package facade;

import dto.MachineDTO;

import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
public interface MachineFacade {

    public void createMachine();
    public void updateMachine();
    public void deleteMachine();
    public MachineDTO findById();
    public List<MachineDTO> findAllMachines();
}
