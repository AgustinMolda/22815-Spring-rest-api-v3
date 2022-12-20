package ar.com.practica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.practica.dominian.Ordenes;
import ar.com.practica.repository.OrdenesRepository;

@Service
@Transactional
public class OrdenesServiceImpl implements OrdenService {

	@Autowired
	private OrdenesRepository repository;
	
	@Override
	public Ordenes save(Ordenes entity) {
	
		return this.repository.save(entity);
	}


	@Override
	public Ordenes getById(Long id) {
		
		return this.repository.findById(id).orElse(Ordenes.builder().build());
	}


	@Override
	public void update(Ordenes ordenDB) {
		this.repository.save(ordenDB);
		
	}


	@Override
	public void eliminar(Long id) {
		this.repository.deleteById(id);
		
	}

}
