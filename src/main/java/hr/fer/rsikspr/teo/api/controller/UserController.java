package hr.fer.rsikspr.teo.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.rsikspr.teo.api.model.User;
import hr.fer.rsikspr.teo.api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody; // vazno



@RestController
@Tag(name="/users", description="Users")
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
	@GetMapping("")
	public List<User> getUsers() {
    	return userService.getAllUsers();
    }
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
    	return userService.getUserById(id)
    			.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
	
	@PostMapping("")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		
		return ResponseEntity.ok(createdUser);
	}
	
	@DeleteMapping("/{id}")
	public Optional<User> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}
