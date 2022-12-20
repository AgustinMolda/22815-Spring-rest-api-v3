package ar.com.practica.controllers;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
				
				Ordenes ordenDb = null;
				
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
					ordenDb = this.ordenService.getById(ordenDb.getId()); 
				
				
				
				return ResponseEntity.status(HttpStatus.CREATED).body(ordenDb);
	}
	
	@PutMapping(value="/orden/{id}")
	public  ResponseEntity<?> put(
			@PathVariable(name = "id",required = true) 
			Long id,
			@RequestBody OrdenDTO ordenDto){
		
				Ordenes ordenDB = this.ordenService.getById(id);
				if(ordenDB == null){
					//404 not found no se econtro el recurso /orden/1
					return	ResponseEntity.notFound().build();
				}
				
				//409 conflict
				if(ordenDB.isEstadoFinal() || !id.equals(ordenDB.getId()) ) {
					var res = new HashMap<String,String>();
					res.put("code", "ABC-001");
					res.put("msj", "ORDEN EN ESTADO INVALIDA");
					return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
				}
				
				//actualizo el/los datos!
				if(ordenDto.getEstadoId() != null) {
					EstadoOrdenes nuevoEstado = EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build();
					ordenDB.setEstado(nuevoEstado);
				}
				
				//Si hay mas datos que modificar ejm monto
				this.ordenService.update(ordenDB);
				
				return ResponseEntity.ok(ordenDB);
			}
				
	
			@GetMapping("/orden/{id}")
		public ResponseEntity<Ordenes> get(
				@PathVariable(name = "id", required = true) 
				
				Long id){
					Ordenes orden =	this.ordenService.getById(id);
					
					if(orden == null || orden.getId() == null) {
						return	ResponseEntity.notFound().build();
					}
					
					return ResponseEntity.ok(orden);
			
		}
				
			@DeleteMapping("/orden/{id}")
			public ResponseEntity<Ordenes> delete(
					@PathVariable(name = "id", required = true) Long id
					){
							try {
								this.ordenService.eliminar(id);
							} catch (RuntimeException re) {
								System.out.println(re.getMessage());
							}
							
							
							return ResponseEntity.ok(null);
			}
}
