package hr.fer.rsikspr.teo.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import java.lang.String;


@RestController
@RequestMapping("/health")
public class HealthController {

	@GetMapping()
    public String health() {
    	return "OK";
    }
    
}