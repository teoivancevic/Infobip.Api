package hr.fer.rsikspr.teo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.rsikspr.teo.api.model.MessageV2;

@Repository
public interface MessageRepositoryV2 extends JpaRepository<MessageV2, Long> {

}
