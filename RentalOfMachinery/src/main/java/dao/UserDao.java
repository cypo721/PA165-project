package dao;


import entity.User;

import java.util.List;

public interface UserDao {
	 public void create(User u);
	 public User findById(Long id);
	 public User findUserByEmail(String email);
	 public  List<User> findAll();
}
