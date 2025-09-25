package com.example.weather_app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weather_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByPreferredLocationIgnoreCase(String city);
	Optional<User> findByEmailAndPassword(String email, String password);
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailIgnoreCase(String email);

}
