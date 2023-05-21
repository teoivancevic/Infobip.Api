package hr.fer.rsikspr.teo.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.rsikspr.teo.api.entities.User; 

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
	Optional<User> findById(Long id);
}