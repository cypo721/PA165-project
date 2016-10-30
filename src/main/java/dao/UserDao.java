package dao;


import entity.User;

import java.util.List;

public interface UserDao {
	/**
	 *
	 * @param u
	 */
	 public void create(User u);

	/**
	 *
	 * @param u
	 */
	 public void update(User u);

	/**
	 *
	 * @param u
	 */
	 public void delete(User u);

	/**
	 *
	 * @param id
	 * @return
	 */
	 public User findById(Long id);

	/**
	 *
	 * @param email
	 * @return
	 */
	 public User findUserByEmail(String email);

	/**
	 *
	 * @return
	 */
	 public  List<User> findAll();
}
