package hr.fer.rsikspr.teo.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.model.ConversationV2;
import hr.fer.rsikspr.teo.api.model.Message;
import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.model.MessageV2;
import hr.fer.rsikspr.teo.api.model.NewMessageV2;
import hr.fer.rsikspr.teo.api.repository.ContentRepository;
import hr.fer.rsikspr.teo.api.repository.MessageRepositoryV2;

@Service
public class MessageServiceV2 {
	private final MessageRepositoryV2 messageRepository;
	private final ContentRepository contentRepository;
	private final ConversationV2Service conversationService;

    public MessageServiceV2(MessageRepositoryV2 messageRepository, ContentRepository contentRepository, ConversationV2Service conversationService) {
		super();
		this.messageRepository = messageRepository;
		this.contentRepository = contentRepository;
		this.conversationService = conversationService;
	}



	public List<MessageV2> getMessages() {
        return messageRepository.findAll();
    }
	
	public MessageV2 createMessage(NewMessageV2 message) {
		
		MessageV2 msgv2 = new MessageV2(message);
		
		msgv2.setContent(contentRepository.save(msgv2.getContent()));
		
		
		List<ConversationV2> conversations = conversationService.getConversationsByParticipants(msgv2.getFrom(), msgv2.getTo());
		List<ConversationV2> activeConv = conversations.stream().filter(c -> c.getEndTime() == null).collect(Collectors.toList());
		
		ConversationV2 conv;
		
		if(activeConv != null && activeConv.size() > 0) {
			conv = activeConv.get(0);
		}
		else {
			conv = conversationService.createConversation(new ConversationV2(msgv2.getFrom(), msgv2.getTo()));
		}
		
		msgv2.setConversation(conv);
		
		
    	return messageRepository.save(msgv2);
    }
}
