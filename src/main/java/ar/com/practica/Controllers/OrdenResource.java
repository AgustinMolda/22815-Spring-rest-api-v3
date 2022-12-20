package ar.com.practica.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.practica.dominian.Cupones;
import ar.com.practica.dominian.EstadoOrdenes;
import ar.com.practica.dominian.Ordenes;
import ar.com.practica.dominian.Socios;
import ar.com.practica.dtos.OrdenDTO;
import ar.com.practica.services.OrdenService;


@RestController
public class OrdenResource {
	
	@Autowired
	private OrdenService ordenService;
	
	
	@PostMapping(value = "/orden")
	public ResponseEntity<Ordenes> post(
			//@Valid
			@RequestBody OrdenDTO ordenDto
			){
				
				Ordenes ordenDb;
				
				if(ordenDto.getId() == null) {
					ordenDb = 	Ordenes.builder()
						.montoTotal(ordenDto.getMontoTotal())
						.socio(Socios.builder().id(ordenDto.getSocioId()).build())
						.estado(EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build())
						.cupon(ordenDto.getCuponId() != null ? Cupones.builder().id(ordenDto.getCuponId()).build() : null)
						.fechaCreacion(new Date())
						.build();
					this.ordenService.save(ordenDb);
				}
				ordenDb = this.ordenService.getById(ordenDto.getId()); 
				
				return ResponseEntity.ok(ordenDb);
	}

}
