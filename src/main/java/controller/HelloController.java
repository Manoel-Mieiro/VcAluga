package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";	//não precisa do .html porque o thymeleaf resolve isso 
	}
	
}
