package hr.fer.rsikspr.teo.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.lang.String;


@RestController
@RequestMapping("/health")
@Api(tags = "My API")
public class HealthController {

	//@RequestMapping(method = RequestMethod.GET, value="health")
	@GetMapping()
    @ApiOperation("Health endpoint")
    public String Health() {
    	return "OK";
    }
    
}