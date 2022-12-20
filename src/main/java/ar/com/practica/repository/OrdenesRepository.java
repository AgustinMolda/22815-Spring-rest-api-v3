package ar.com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.practica.dominian.Ordenes;

@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Long> {

	//Ordenes save(Ordenes entity);
	
}
