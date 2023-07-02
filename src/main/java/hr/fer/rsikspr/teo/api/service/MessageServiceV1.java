package hr.fer.rsikspr.teo.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.model.Message;
import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.model.User;
import hr.fer.rsikspr.teo.api.repository.MessageRepositoryV1;
import hr.fer.rsikspr.teo.api.repository.UserRepository;

@Service
public class MessageServiceV1   {
	
	private final MessageRepositoryV1 messageRepository;
	
	private final ConversationV1Service conversationService;

    public MessageServiceV1(MessageRepositoryV1 messageRepository, ConversationV1Service conversationService) {
		super();
		this.messageRepository = messageRepository;
		this.conversationService = conversationService;
	}



	public List<MessageV1> getAllMessages() {
        return messageRepository.findAll();
    }
	
	public MessageV1 createMessage(Message message) {
		
		MessageV1 msgv1 = new MessageV1(message);
		
		List<ConversationV1> conversations = conversationService.getConversationsByParticipants(msgv1.getFrom(), msgv1.getTo());
		List<ConversationV1> activeConv = conversations.stream().filter(c -> c.getEndTime() == null).collect(Collectors.toList());
		
		ConversationV1 conv;
		
		if(activeConv != null && activeConv.size() > 0) {
			conv = activeConv.get(0);
		}
		else {
			conv = conversationService.createConversation(new ConversationV1(msgv1.getFrom(), msgv1.getTo()));
		}
		
		msgv1.setConversation(conv);
		
    	return messageRepository.save(msgv1);
    }
}
