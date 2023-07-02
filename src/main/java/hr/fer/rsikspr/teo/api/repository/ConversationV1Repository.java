package hr.fer.rsikspr.teo.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.model.MessageV1;

@Repository
public interface ConversationV1Repository extends JpaRepository<ConversationV1, Long> {
	
	@Query("SELECT c FROM ConversationV1 c WHERE (c.participant1 = :participant1 AND c.participant2 = :participant2) OR (c.participant1 = :participant2 AND c.participant2 = :participant1)")
    List<ConversationV1> findByParticipants(String participant1, String participant2);
	
	@Query("SELECT c FROM ConversationV1 c WHERE (c.participant1 = :name OR c.participant2 = :name)")
    List<ConversationV1> findByUser(String name);
}
