package ar.com.practica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.practica.dominian.Users;


public interface UserService {
	public Optional<Users> obetenerPorId(Long id);
}
