/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Rental;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marek Bohm
 */
@Transactional
@Repository
public class RentalDaoImpl implements RentalDao {
    
        @PersistenceContext
	private EntityManager em;

    @Override
    public void create(Rental rental) {
        em.persist(rental);
    }

    @Override
    public void update(Rental rental) throws IllegalArgumentException {
        em.merge(rental); 
    }

    @Override
    public void delete(Rental rental) throws IllegalArgumentException{
        em.remove(em.contains(rental) ? rental : em.merge(rental));
    }

    @Override
    public Rental findById(Long id) {
        return em.find(Rental.class, id);
    }

    @Override
    public List<Rental> findAllRentals() {
    TypedQuery<Rental> query = em.createQuery("SELECT rental FROM Rental rental", Rental.class);
		return (List<Rental>) query.getResultList();  
    }
    
}
