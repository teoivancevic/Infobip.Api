package hr.fer.rsikspr.teo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.String;


@RestController
@Tag(name="/health", description="")
@RequestMapping("/health")
public class HealthController {

	@GetMapping()
    public String health() {
    	return "OK";
    }
    
}