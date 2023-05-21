package hr.fer.rsikspr.teo.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.rsikspr.teo.api.repositories.UserRepository;
import hr.fer.rsikspr.teo.api.entities.User;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User createUser(User user) {
    	return userRepository.save(user);
    }
    
    public Optional<User> deleteUser(Long id) {
    	Optional<User> user = userRepository.findById(id); 
    	if(user.get() != null) {
    		userRepository.delete(user.get());
    	}
    	
    	return user;
    	
    	
    	
    }
}