package com.example;

import java.util.List;


public interface UserService {

	List<User> getUsers();

	boolean saveUser(User user);

	User getUserByEmail(String email);

	boolean deleteUserById(Long id);


}
