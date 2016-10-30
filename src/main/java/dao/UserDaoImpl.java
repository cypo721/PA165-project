package dao;


import entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(User u) {
		em.persist(u);
	}

	@Override
	public void update(User u) {
		em.merge(u);
	}

	@Override
	public void delete(User u) {
		em.remove(u);
	}

	@Override
	public User findUserByEmail(String email) {
		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("Cannot search for null e-mail");

		try {
			return em
					.createQuery("select u from User u where email=:email",
							User.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public User findById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
				User.class);
		return (List<User>) query.getResultList();
	}

}
