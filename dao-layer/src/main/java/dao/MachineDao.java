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
     * Create persistent representation of machine in database.
     * @param machine 
     */    
     public void create(Machine machine);
    
     /**
     * Update machine item in database
     * @param machine item to be updated      
     */
     public void update(Machine machine);
   
     /**
     * Delete machine item in database
     * @param machine item to be deleted
     */
     public void delete(Machine machine);
    
     /**
     * Find machine by id
     * @param id of desired machine 
     * @return found machine item
      */
     public Machine findById(Long id);
    
     /**
     * Finds list of all machines in persistent storage 
     * @return list of all machines in storage
     */
     public List<Machine> findAllMachines();
 }
