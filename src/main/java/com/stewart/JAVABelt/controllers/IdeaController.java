package com.stewart.JAVABelt.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stewart.JAVABelt.models.Idea;
import com.stewart.JAVABelt.models.User;
import com.stewart.JAVABelt.services.IdeaService;
import com.stewart.JAVABelt.services.UserService;

@Controller
@RequestMapping("/ideas")
public class IdeaController {
	private UserService userServ;
	private IdeaService ideaServ;
	
	public IdeaController(UserService userServ, IdeaService ideaServ) {
		this.userServ = userServ;
		this.ideaServ = ideaServ;
	}
	
	@GetMapping("")
	public String ideas(HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		session.setAttribute("userObj", userServ.findUserById((long) session.getAttribute("user")));
		model.addAttribute("ideas", ideaServ.findAllIdeas());
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newIdeaForm(@ModelAttribute("creIdea") Idea idea) {
		return "newIdea.jsp";
	}
	
	@PostMapping("/new")
	public String newIdeaSub(@Valid @ModelAttribute("creIdea") Idea idea, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "newIdea.jsp";
		}
		long user_id = (long) session.getAttribute("user");
		User creator = userServ.findUserById(user_id);
		idea.setUser(creator);
		ideaServ.createIdea(idea);
		return "redirect:/ideas";
	}
	
	@PostMapping("/like/{id}")
	public String like(@PathVariable("id") long id, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		Idea voted = ideaServ.findIdeaById(id);
		long user_id = (long) session.getAttribute("user");
		User fullUser = userServ.findUserById(user_id);
		
		List<User> listUser = voted.getUsers();
		
		if(!listUser.contains(fullUser)){
			listUser.add(fullUser);
			voted.setUsers(listUser);
			ideaServ.updateIdea(voted);
		}
		
		return "redirect:/ideas";
	}
	
	@PostMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") long id, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		
		Idea voted = ideaServ.findIdeaById(id);
		long user_id = (long) session.getAttribute("user");
		User fullUser = userServ.findUserById(user_id);
		
		List<User> listUser = voted.getUsers();
		
		if(listUser.contains(fullUser)){
			listUser.remove(fullUser);
			voted.setUsers(listUser);
			ideaServ.updateIdea(voted);
		}
		return "redirect:/ideas";
	}
	
	@GetMapping("/{id}")
	public String info(@PathVariable("id") long id, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		
		Idea ideaObj = ideaServ.findIdeaById(id);
		User creator = userServ.findUserById((long) session.getAttribute("user"));
		if(creator == ideaObj.getUser()) {
			boolean validUser = true;
			model.addAttribute("isUser", validUser);
		} else {
			boolean validUser = false;
			model.addAttribute("isUser", validUser);
		}
		model.addAttribute("idea", ideaObj);
		
		return "ideaInfo.jsp";
	}
	
	@GetMapping("/edit/{id}")
	public String showEdit(@Valid @ModelAttribute("editIdea") Idea idea, BindingResult result, @PathVariable("id") long id, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
//		if(result.hasErrors()) {
//			System.out.println("error Here");
////			return "editIdea.jsp";
//		}
		Idea ideaObj = ideaServ.findIdeaById(id);
		User creator = ideaObj.getUser();
		User sessUser = userServ.findUserById((long) session.getAttribute("user"));
		if(creator != sessUser) {
			return "redirect:/ideas/" + id;
		}
		
		model.addAttribute("idea", ideaServ.findIdeaById(id));
		return "editIdea.jsp";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@Valid @ModelAttribute("editIdea") Idea idea, BindingResult result, @PathVariable("id") long id, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		
		if(result.hasErrors()) {
			return "editIdea.jsp";
		}
		
		Idea editIdea = ideaServ.findIdeaById(id);
		
		if(userServ.findUserById((long) session.getAttribute("user")).equals(editIdea.getUser())) {
			editIdea.setIdea(idea.getIdea());
			ideaServ.updateIdea(editIdea);
		} 
		
		return "redirect:/ideas/" + id;
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		ideaServ.deleteIdeaById(id);
		return "redirect:/ideas";
	}

}
