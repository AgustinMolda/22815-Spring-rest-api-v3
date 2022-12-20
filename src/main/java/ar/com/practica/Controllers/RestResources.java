package ar.com.practica.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.practica.dominian.User;


@RestController
public class RestResources {
	
	@Autowired	
	private User user;
	
	@RequestMapping("/home")
	public void home() {
		this.user.getId();
		System.out.println("uscuchando en /");
	}
	
	@RequestMapping("/user/show")
	public User showUser() {
		User u = new User();
		
		return u;
	}
	
	
}
