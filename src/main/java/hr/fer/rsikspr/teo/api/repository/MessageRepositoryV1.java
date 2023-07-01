package hr.fer.rsikspr.teo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.model.User;

@Repository
public interface MessageRepositoryV1 extends JpaRepository<MessageV1, Long> {

}
