package ar.com.practica.services;


import ar.com.practica.dominian.Ordenes;


public interface OrdenService {
	public Ordenes save(Ordenes entity);

	public Ordenes getById(Long id);

	public void update(Ordenes ordenDB);

	public void eliminar(Long id);

	

	
}
