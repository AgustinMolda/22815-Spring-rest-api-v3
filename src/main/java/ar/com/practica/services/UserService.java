package ar.com.practica.services;

import java.util.Optional;



import ar.com.practica.dominian.Users;


public interface UserService {
	public Optional<Users> obetenerPorId(Long id);
}
