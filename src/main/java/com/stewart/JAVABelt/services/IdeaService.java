package com.stewart.JAVABelt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stewart.JAVABelt.models.Idea;
import com.stewart.JAVABelt.repositories.IdeaRepository;

@Service
public class IdeaService {
	
	private final IdeaRepository ideaRepo;
	
	public IdeaService(IdeaRepository ideaRepo) {
		this.ideaRepo = ideaRepo;
	}
	
//	C
	public Idea createIdea(Idea idea) {
		return ideaRepo.save(idea);
	}
	
//	R
	public ArrayList<Idea> findAllIdeas(){
		return (ArrayList<Idea>) ideaRepo.findAll();
	}
	public Idea findIdeaById(Long id) {
		Optional<Idea> optionalIdea = ideaRepo.findIdeaById(id);
		if(optionalIdea.isPresent()) {
			return optionalIdea.get();
		} else {
			return null;
		}
	}
	
//	public ArrayList<Idea> findAllByASC(){
//		return (ArrayList<Idea>) ideaRepo.findAllByASC();
//	}
	
//	U
	public Idea updateIdea(Idea diffIdea) {
		return ideaRepo.save(diffIdea);
	}
	
//	D
	public void deleteIdeaById(Long id) {
		ideaRepo.deleteById(id);
	}
}
