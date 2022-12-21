package ar.com.practica.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component	
public class JwtProvider {
	public boolean validateToken(String jwtToken) {
		return true;
	}
	
	public  String getUsernameFromToken(String jwtToken){
			
		return "eduit";
	}
	
	public String generateToken(Authentication auth) {
			return "jwt de mentira por que despues lo cambiamos";
	}
	
}
