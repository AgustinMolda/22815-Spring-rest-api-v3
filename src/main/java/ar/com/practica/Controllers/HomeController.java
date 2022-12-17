package ar.com.practica.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.practica.dominian.User;



@Controller

public class HomeController {
	

	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
