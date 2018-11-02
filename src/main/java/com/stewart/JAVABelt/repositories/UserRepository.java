package com.stewart.JAVABelt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.stewart.JAVABelt.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
	List<User> findAll();
	Optional<User> findUserById(Long id);

}
