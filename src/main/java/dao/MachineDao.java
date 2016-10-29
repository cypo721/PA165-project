/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Machine;
import java.util.List;

/**
 *
 * @author eduard
 */
public interface MachineDao {
    
    /**
     * 
     * @param machine 
     */    
    public void create(Machine machine);
   
    /**
     * 
     * @param machine 
     */
    public void update(Machine machine);
  
    /**
     * 
     * @param machine 
     */
    public void delete(Machine machine);
   
    /**
     * 
     * @param id
     * @return 
     */
    public Machine findById(Long id);
   
    /**
     * 
     * @return 
     */
    public List<Machine> findAllMachines();
}
