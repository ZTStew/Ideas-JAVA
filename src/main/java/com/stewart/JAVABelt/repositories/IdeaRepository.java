package com.stewart.JAVABelt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.stewart.JAVABelt.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea,Long> {
	List<Idea> findAll();
	Optional<Idea> findIdeaById(Long id);
//	List<Idea> findAllOrderByUsersAsc();
//	@Query(
//	value = "SELECT * FROM ideas ORDER BY likes ASC",
//	nativeQuery = true
//	)
//	List<Idea> findAllByASC();
	
	
}
