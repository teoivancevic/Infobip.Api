package hr.fer.rsikspr.teo.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.model.MessageV2;
import hr.fer.rsikspr.teo.api.repository.MessageRepositoryV2;

@Service
public class MessageServiceV2 {
	private final MessageRepositoryV2 messageRepository;

    public MessageServiceV2(MessageRepositoryV2 messageRepository) {
		super();
		this.messageRepository = messageRepository;
	}



	public List<MessageV2> getMessages() {
        return messageRepository.findAll();
    }
	
	public MessageV2 createMessage(MessageV2 message) {
    	return messageRepository.save(message);
    }
}
