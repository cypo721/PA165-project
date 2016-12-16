package service;

import dao.MachineDao;
import entity.Machine;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
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
        Validate.isTrue(machine.getId() == null);
        machineDao.create(machine);

        return machine;
    }

    @Override
    public void update(Machine machine) {
        Validate.notNull(machine.getId());
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

    @Override
    public List<Machine> getMachinesInLastYearWithoutRevision() {
        List<Machine> machines = findAllMachines();
        List<Machine> unrevisionedMachines = new ArrayList<>();
        Calendar yearAgo = Calendar.getInstance();
        yearAgo.add(Calendar.YEAR, -1);

        for(int i = 0; i < machines.size(); i++){
            Machine m = machines.get(i);
            if( m.getDateOfLastRevision() == null) {
                if (m.getDateOfBuy().before(yearAgo.getTime())){
                    unrevisionedMachines.add(m);
                }
            }
            else {
                if (m.getDateOfLastRevision().before(yearAgo.getTime())) {
                    unrevisionedMachines.add(m);
                }
            }
        }

        return unrevisionedMachines;
    }
}
