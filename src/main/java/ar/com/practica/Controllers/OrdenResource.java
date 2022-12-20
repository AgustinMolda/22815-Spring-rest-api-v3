package ar.com.practica.controllers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.practica.dominian.Users;
import ar.com.practica.dtos.UsersDTO;
import ar.com.practica.services.UserService;

@RestController
public class OrdenResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/orden/user")
	public UsersDTO user(){
		
		Optional<Users> users = this.service.obetenerPorId(1l);	
		UsersDTO dto = null;
		if(!users.isEmpty()){
			Set<String> rolesStrs = users.get().getRoles()
					.stream()
					.map(r -> "ROLE_" + r.getRole())
					.collect(Collectors.toSet());
			
			dto =  UsersDTO.builder()
				.username(users.get().getUsername())
				.roles(rolesStrs)
				.build(); 
		
		
			
		}
		return dto;
	}
}
