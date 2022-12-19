package ar.com.practica.dominian;

import lombok.Data;

@Data
public class EstadoOrdenes {
	private Long id;
	private String descripcion;
	private String descripcionCorta;
	private Integer estadoFinal; // boolean estado 0 false, estado 1 true	
}
