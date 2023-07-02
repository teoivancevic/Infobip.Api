package hr.fer.rsikspr.teo.api.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.repository.ConversationV1Repository;
import hr.fer.rsikspr.teo.api.repository.MessageRepositoryV1;

@Service
public class ConversationV1Service {
	
	private final ConversationV1Repository conversationRepository;

    public ConversationV1Service(ConversationV1Repository conversationRepository) {
		super();
		this.conversationRepository = conversationRepository;
	}
    
    public List<ConversationV1> getAllConversations(){
    	return conversationRepository.findAll();
    }
    
    public Optional<ConversationV1> getConversationById(long id) {
    	return conversationRepository.findById(id);
    }
    
    public List<ConversationV1> getConversationsByParticipants(String part1, String part2) {
    	return conversationRepository.findByParticipants(part1, part2);
    }
    
    public List<ConversationV1> getConversationsByUser(String name){
    	return conversationRepository.findByUser(name);
    }
    
    public ConversationV1 getActiveConversationByUser(String name){
    	List<ConversationV1> conversations = conversationRepository.findByUser(name);
    	List<ConversationV1> activeConversations = conversations.stream().filter(c -> c.getEndTime() == null).collect(Collectors.toList());
    	
    	if(activeConversations != null && activeConversations.size() > 0) {
    		return activeConversations.get(0);
    	}
    	
    	return null;
    	
	}
	
	public ConversationV1 createConversation(ConversationV1 conv) {
    	return conversationRepository.save(conv);
    }
	
	public boolean closeConversationById(long id) {
		Optional<ConversationV1> convv = conversationRepository.findById(id);
		ConversationV1 conv = convv.get();
		
		if(conv != null) {
			conv.setEndTime(LocalDateTime.now());
			conversationRepository.save(conv);
			return true; // conv closed
		}
		
		return false; // no active conv
	}
	
	public boolean closeConversationForUser(String name) {
		ConversationV1 conv = getActiveConversationByUser(name);
		
		if(conv != null) {
			conv.setEndTime(LocalDateTime.now());
			conversationRepository.save(conv);
			return true; // conv closed
		}
		
		return false; // no active conv
	}
	
	
	public List<ConversationV1> getConversationsInTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return conversationRepository.findByStartTimeBetween(startTime, endTime);
    }
}
