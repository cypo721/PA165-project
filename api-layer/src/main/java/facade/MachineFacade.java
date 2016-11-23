package facade;

import dto.MachineDTO;

import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
public interface MachineFacade {

    public Long createMachine(MachineDTO m);
    public void updateMachine(MachineDTO m);
    public void deleteMachine(Long id);
    public MachineDTO findById(Long id);
    public List<MachineDTO> findAllMachines();
}
