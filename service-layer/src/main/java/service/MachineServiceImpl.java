package service;

import dao.MachineDao;
import entity.Machine;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pato on 22.11.2016.
 */
public class MachineServiceImpl implements MachineService {

    @Inject
    private MachineDao machineDao;

    @Override
    public void create(Machine machine) {

    }

    @Override
    public void update(Machine machine) {

    }

    @Override
    public void delete(Machine machine) {

    }

    @Override
    public Machine findById(Long id) {
        return null;
    }

    @Override
    public List<Machine> findAllMachines() {
        return null;
    }
}
