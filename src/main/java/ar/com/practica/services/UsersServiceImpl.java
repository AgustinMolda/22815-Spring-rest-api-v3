package ar.com.practica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.practica.dominian.Users;
import ar.com.practica.repository.UserRepository;

@Service
@Transactional
public class UsersServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public  Optional<Users> obetenerPorId(Long id) {
		
		return this.userRepository.findById(id);
	}

}
