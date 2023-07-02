package hr.fer.rsikspr.teo.api.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.model.ConversationV2;
import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.repository.ConversationV1Repository;
import hr.fer.rsikspr.teo.api.repository.ConversationV2Repository;
import hr.fer.rsikspr.teo.api.repository.MessageRepositoryV1;

@Service
public class ConversationV2Service {
	
	private final ConversationV2Repository conversationRepository;

    public ConversationV2Service(ConversationV2Repository conversationRepository) {
		super();
		this.conversationRepository = conversationRepository;
	}
    
    public List<ConversationV2> getAllConversations(){
    	return conversationRepository.findAll();
    }
    
    public Optional<ConversationV2> getConversationById(long id) {
    	return conversationRepository.findById(id);
    }
    
    public List<ConversationV2> getConversationsByParticipants(String part1, String part2) {
    	return conversationRepository.findByParticipants(part1, part2);
    }
    
    public List<ConversationV2> getConversationsByUser(String name){
    	return conversationRepository.findByUser(name);
    }
    
    public ConversationV2 getActiveConversationByUser(String name){
    	List<ConversationV2> conversations = conversationRepository.findByUser(name);
    	List<ConversationV2> activeConversations = conversations.stream().filter(c -> c.getEndTime() == null).collect(Collectors.toList());
    	
    	if(activeConversations != null && activeConversations.size() > 0) {
    		return activeConversations.get(0);
    	}
    	
    	return null;
    	
	}
	
	public ConversationV2 createConversation(ConversationV2 conv) {
    	return conversationRepository.save(conv);
    }
	
	public boolean closeConversationById(long id) {
		Optional<ConversationV2> convv = conversationRepository.findById(id);
		ConversationV2 conv = convv.get();
		
		if(conv != null) {
			conv.setEndTime(LocalDateTime.now());
			conversationRepository.save(conv);
			return true; // conv closed
		}
		
		return false; // no active conv
	}
	
	public boolean closeConversationForUser(String name) {
		ConversationV2 conv = getActiveConversationByUser(name);
		
		if(conv != null) {
			conv.setEndTime(LocalDateTime.now());
			conversationRepository.save(conv);
			return true; // conv closed
		}
		
		return false; // no active conv
	}
	
	public List<ConversationV2> getConversationsInTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return conversationRepository.findByStartTimeBetween(startTime, endTime);
    }
	
}
