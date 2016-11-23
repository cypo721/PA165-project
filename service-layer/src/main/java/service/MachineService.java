package service;

import entity.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
@Service
public interface MachineService {
    public void create(Machine machine);
    public void update(Machine machine);
    public void delete(Machine machine);
    public Machine findById(Long id);
    public List<Machine> findAllMachines();
}
