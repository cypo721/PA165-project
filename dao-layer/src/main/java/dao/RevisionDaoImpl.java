/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Revision;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Václav Zouzalík
 */
@Transactional
@Repository
public class RevisionDaoImpl implements RevisionDao {
    @PersistenceContext
	private EntityManager em;
    
    @Override
    public void create(Revision revision) {
        em.persist(revision);
    }

    @Override
    public void update(Revision revision) throws IllegalArgumentException {
        em.merge(revision); 
    }

    @Override
    public void delete(Revision revision) throws IllegalArgumentException{
        em.remove(em.contains(revision) ? revision : em.merge(revision));
    }

    @Override
    public Revision findById(Long id) {
        return em.find(Revision.class, id);
    }

    @Override
    public List<Revision> findAllRevisions(){
        TypedQuery<Revision> query = em.createQuery("SELECT revision FROM Revision revision", Revision.class);
        return (List<Revision>) query.getResultList();
    }
}
