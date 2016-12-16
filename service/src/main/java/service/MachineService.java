package service;

import entity.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
@Service
public interface MachineService {
    /**
     * Create new machine
     * @param machine to be created
     * @return created machine
     */
    public Machine create(Machine machine);

    /**
     * Update given machine
     * @param machine with values to be updated
     */
    public void update(Machine machine);

    /**
     * Delete given machine
     * @param machine to be delete
     */
    public void delete(Machine machine);

    /**
     * Find machine by given id
     * @param id machine id to be found
     * @return found machine
     */
    public Machine findById(Long id);

    /**
     * Return all machines.
     * @return List of machines.
     */
    public List<Machine> findAllMachines();

    /**
     * Return all machines which dont have revison more than year,
     * or were bought more than year ago and dont have revision
     * @return all unrevisioned machines in last year
     */
    public List<Machine> getMachinesInLastYearWithoutRevision();
}
