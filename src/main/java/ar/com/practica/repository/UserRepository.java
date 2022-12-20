package ar.com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.practica.dominian.Users;



@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
