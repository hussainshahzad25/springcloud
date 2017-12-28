package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepository;
	
	

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUsers() {
	
		return (List<User>) userRepository.findAll();
	}

	@Override
	public boolean saveUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByEmail(String firstName) {
		
		return userRepository.getByFirstName(firstName);
	}

	@Override
	public boolean deleteUserById(Long id) {
		
		try {
			userRepository.delete(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	

	
}
