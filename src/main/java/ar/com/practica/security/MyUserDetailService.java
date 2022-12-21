package ar.com.practica.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.practica.dominian.Users;
import ar.com.practica.services.UserService;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users users = this.userService.findByName(username);
		
	Set<SimpleGrantedAuthority> roles =	users.getRoles()
		.stream()
		.map(r -> new SimpleGrantedAuthority(r.getRole()))
		.collect(Collectors.toSet());
		
		
		return new User(users.getUsername(),users.getPassword(),roles);
	}

}
