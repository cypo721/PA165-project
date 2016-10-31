/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Revision;

import java.util.List;

/**
 *
 * @author Václav Zouzalík
 */
public interface RevisionDao {
    /**
     * 
     * @param revision 
     */    
    public void create(Revision revision);
   
    /**
     * 
     * @param revision 
     */
    public void update(Revision revision);
  
    /**
     * 
     * @param revision 
     */
    public void delete(Revision revision);
   
    /**
     * 
     * @param id
     * @return Revision
     */
    public Revision findById(Long id);

    /**
     * Finds list of all revisions in persistent storage
     * @return list of all revisions in storage
     */
    public List<Revision> findAllRevisions();
}
