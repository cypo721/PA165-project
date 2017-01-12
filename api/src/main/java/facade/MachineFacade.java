package facade;

import dto.MachineDTO;

import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
public interface MachineFacade {

    /**
     * Create new machine
     * @param m machine to be created
     * @return id of new machine
     */
    public Long createMachine(MachineDTO m);

    /**
     * Update given machine
     * @param m machine to be updated
     */
    public void updateMachine(MachineDTO m);

    /**
     * Delete machine with given id
     * @param id of machine to be deleted
     */
    public void deleteMachine(Long id);

    /**
     * Find machine with given id
     * @param id of machine to be found
     * @return machine with given id
     */
    public MachineDTO findById(Long id);

    /**
     * Find all machines.
     * @return List of all machines.
     */
    public List<MachineDTO> findAllMachines();

    /**
     * Return all machines which dont have revison more than year,
     * or were bought more than year ago and dont have revision
     * @return all unrevisioned machines in last year
     */
    public List<MachineDTO> getMachinesInLastYearWithoutRevision();

    /**
     * Return list of free machines.
     * @return list of free machines.
     */
    public List<MachineDTO> getFreeMachines();
}
