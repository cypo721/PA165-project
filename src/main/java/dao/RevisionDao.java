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
     * Create persistent representation of revision in database.
     * @param revision revision to persist
     */    
    public void create(Revision revision);
   
    /**
     * Update revision in database
     * @param revision revision to update
     */
    public void update(Revision revision);
  
    /**
     * Delete revision from database
     * @param revision revision to delete 
     */
    public void delete(Revision revision);
   
    /**
     * Find revision in database by given id
     * @param id id of a wanted revision
     * @return Revision with given id
     */
    public Revision findById(Long id);

    /**
     * Finds list of all revisions in persistent storage
     * @return list of all revisions in storage
     */
    public List<Revision> findAllRevisions();
}
