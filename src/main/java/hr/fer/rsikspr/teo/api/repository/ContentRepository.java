package hr.fer.rsikspr.teo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.rsikspr.teo.api.model.Content;
import hr.fer.rsikspr.teo.api.model.ConversationV1;

public interface ContentRepository extends JpaRepository<Content, Long>{

}
