package ar.com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.practica.dominian.Users;



@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
		
	public Users findByUsername(String name);
	//public Users findByUsernameAndLastname(String name);
	
	/*
	 * @Query(
	 * 	nativeQuery = true, value = "select * from tabla que necesites where, 
	 * name = "buscarAJuanQuery"
	 * )
	 * public Users buscarAJuan();
	 * */
}
