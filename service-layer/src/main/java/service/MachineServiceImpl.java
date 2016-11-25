package service;

import dao.MachineDao;
import entity.Machine;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
@Service
public class MachineServiceImpl implements MachineService {

    @Inject
    private MachineDao machineDao;

    @Override
    public Machine create(Machine machine) {
        machineDao.create(machine);

        return machine;
    }

    @Override
    public void update(Machine machine) {
        machineDao.update(machine);
    }

    @Override
    public void delete(Machine machine) {
        machineDao.delete(machine);
    }

    @Override
    public Machine findById(Long id) {
        return machineDao.findById(id);
    }

    @Override
    public List<Machine> findAllMachines() {
        return machineDao.findAllMachines();
    }
}
