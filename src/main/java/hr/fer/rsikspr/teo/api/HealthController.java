package hr.fer.rsikspr.teo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.lang.String;


@RestController
@RequestMapping("api/health")

public class HealthController {

	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping
    public String Health() {
    	return "OK";
    }
    
}