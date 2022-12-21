package ar.com.practica.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
		
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		String jwt = getToken(request);
		
		if(jwt == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!this.jwtProvider.validateToken(jwt)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String usernameFromJwtToken =  this.jwtProvider.getUsernameFromToken(jwt);
		
		UserDetails usersDetails =  myUserDetailService.loadUserByUsername(usernameFromJwtToken);
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usersDetails,null, usersDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth); 
		
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		 String header = request.getHeader("Authorization");
		
		 
		 
		 
		 /*if(header != null){
			if(header.startsWith("Bearer")) {
				return header.replace("Bearer", "");
			}
		}*/
		 
		 if(header == null) {
			 return header;
		 }
		 
		 if(!header.startsWith("Bearer")) {
			 return header;
		 }
		
		 return header.replace("Bearer", "");
	}
}
