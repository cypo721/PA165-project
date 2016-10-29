/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Machine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eduard
 */
@Repository
public class MachineDaoImpl implements MachineDao {
    
    @PersistenceContext
	private EntityManager em;

    @Override
    public void create(Machine machine) {
        em.persist(machine);
    }

    @Override
    public void update(Machine machine) throws IllegalArgumentException {
        em.merge(machine); 
    }

    @Override
    public void delete(Machine machine) throws IllegalArgumentException{
        em.remove(machine);
    }

    @Override
    public Machine findById(Long id) {
        return em.find(Machine.class, id);
    }

    @Override
    public List<Machine> findAllMachines() {
    TypedQuery<Machine> query = em.createQuery("SELECT machine FROM Machine machine", Machine.class);
		return (List<Machine>) query.getResultList();  
    }
    
}
