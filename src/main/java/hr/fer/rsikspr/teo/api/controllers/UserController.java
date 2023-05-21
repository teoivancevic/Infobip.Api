package hr.fer.rsikspr.teo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.rsikspr.teo.api.entities.User;
import hr.fer.rsikspr.teo.api.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
	
    @GetMapping("/test")
	public String Test() {
    	return "test";
    }
    
	@GetMapping("")
	public List<User> GetUsers() {
    	return userService.getAllUsers();
    }
	
	@GetMapping("/{id}")
	public User GetUserById(@PathVariable Long id) {
    	return userService.getUserById(id)
    			.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
