package ar.com.practica.dominian;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private Long id;
	private String username;
	private String password;

	
}
