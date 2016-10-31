package dao;


import entity.User;

import java.util.List;

public interface UserDao {
	/**
	 * Create persistent representation of userin database.
	 * @param u User to persist
	 */
	 public void create(User u);

	/**
	 * Update user in database.
	 * @param u user to update.
	 */
	 public void update(User u);

	/**
	 * Delete user from database.
	 * @param u user to delete.
	 */
	 public void delete(User u);

	/**
	 * Find user in database by given id.
	 * @param id user id to be found.
	 * @return User with given id.
	 */
	 public User findById(Long id);

	/**
	 * Find user by given email.
	 * @param email user's email to be found.
	 * @return User with given email.
	 */
	 public User findUserByEmail(String email);

	/**
	 * Method for retrieving all users form database.
	 * @return All users from database.
	 */
	 public  List<User> findAll();
}
