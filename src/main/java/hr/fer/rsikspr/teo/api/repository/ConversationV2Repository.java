package hr.fer.rsikspr.teo.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.model.ConversationV2;
import hr.fer.rsikspr.teo.api.model.MessageV1;

@Repository
public interface ConversationV2Repository extends JpaRepository<ConversationV2, Long> {
	
	@Query("SELECT c FROM ConversationV2 c WHERE (c.participant1 = :participant1 AND c.participant2 = :participant2) OR (c.participant1 = :participant2 AND c.participant2 = :participant1)")
    List<ConversationV2> findByParticipants(String participant1, String participant2);
	
	@Query("SELECT c FROM ConversationV2 c WHERE (c.participant1 = :name OR c.participant2 = :name)")
    List<ConversationV2> findByUser(String name);
	
	List<ConversationV2> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
