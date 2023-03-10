package ar.com.practica.controllers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.com.practica.dominian.Users;
import ar.com.practica.dtos.UsersDTO;
import ar.com.practica.services.UserService;

@RestController
public class UsersResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/users/{id}")
	public UsersDTO get(
			@PathVariable(name = "id", required = true)
			Long id
			
			) {
			Optional<Users> users = this.userService.obetenerPorId(id);	
		
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
	
	@GetMapping(value = "/users/name/{name}")
	public ResponseEntity<Users> getUserByName(
			@PathVariable(name = "name", required = true) String name
			
			){
		        return ResponseEntity.ok(this.userService.findByName(name));
		
	}
	
	
}
