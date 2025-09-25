package com.example.weather_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.weather_app.entity.User;
import com.example.weather_app.repo.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public Optional<User> getUserById(Long id){
		return userRepo.findById(id);
	}
	
	public void deleteUser(Long id) {
		 userRepo.deleteById(id);
	}
	
	public Optional<User> login(String email, String password) {
	    return userRepo.findByEmailAndPassword(email, password);
	}

	
}
	
